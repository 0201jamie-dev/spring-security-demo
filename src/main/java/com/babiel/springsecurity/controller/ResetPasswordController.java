package com.babiel.springsecurity.controller;

import com.babiel.springsecurity.model.BlacklistEntity;
import com.babiel.springsecurity.model.ResetPasswordForm;
import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.service.BlacklistService;
import com.babiel.springsecurity.service.CaptchaService;
import com.babiel.springsecurity.service.JWTService;
import com.babiel.springsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Controller
public class ResetPasswordController {
    private final JWTService jwtService;
    private final UserService userService;
    private final BlacklistService blacklistService;
    private final CaptchaService captchaService;
    private final PasswordEncoder encoder;

    public ResetPasswordController(JWTService jwtService, UserService userService, PasswordEncoder encoder,  BlacklistService blacklistService, CaptchaService captchaService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.encoder = encoder;
        this.blacklistService = blacklistService;
        this.captchaService = captchaService;
    }

    @GetMapping("/resetPassword")
    public String displayResetPassword(@RequestParam String jwt, Model model, @ModelAttribute ResetPasswordForm resetPasswordForm, HttpServletRequest request) throws UnsupportedEncodingException {
        if (!jwtService.isTokenValid(jwt) || blacklistService.isTokenBlacklisted(jwt)) {
            model.addAttribute("isValid", false);
            return "resetPassword";
        }
        model.addAttribute("jwt", jwt);
        model.addAttribute("isValid", true);

        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@Valid @ModelAttribute ResetPasswordForm resetPasswordForm,
                                BindingResult result, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String response = request.getParameter("g-recaptcha-response");
        String clientIp = request.getHeader("X-FORWARDED-FOR");

        if (clientIp == null) {
            clientIp = request.getRemoteAddr();
        }

        if (!captchaService.verify(response, clientIp)) {
            model.addAttribute("isCaptchaInvalid", true);
            return "resetPassword";
        }

        model.addAttribute("isValid", true);

        if (result.hasErrors()) {
            return "resetPassword";
        }

        if (!jwtService.isTokenValid(resetPasswordForm.token()) || blacklistService.isTokenBlacklisted(resetPasswordForm.token())) {
            return "resetPassword";
        }

        UserEntity user = userService.getUserByEmailAddress(jwtService.getSubject(resetPasswordForm.token()));
        user.setPassword(encoder.encode(resetPasswordForm.password()));
        userService.updateUser(user);
        blacklistService.addToken(new BlacklistEntity(resetPasswordForm.token(), jwtService.getExpiryDate(resetPasswordForm.token())));

        return "redirect:/login";
    }
}
