package com.babiel.springsecurity.controller;

import com.babiel.springsecurity.model.BlacklistEntity;
import com.babiel.springsecurity.model.ResetPasswordForm;
import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.model.UserPrincipal;
import com.babiel.springsecurity.service.BlacklistService;
import com.babiel.springsecurity.service.CaptchaService;
import com.babiel.springsecurity.service.JWTService;
import com.babiel.springsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
@Slf4j
public class PasswordExpiredController {
    private final UserService userService;
    private final CaptchaService captchaService;
    private final PasswordEncoder encoder;

    public PasswordExpiredController(JWTService jwtService, UserService userService, PasswordEncoder encoder, BlacklistService blacklistService, CaptchaService captchaService) {
        this.userService = userService;
        this.encoder = encoder;
        this.captchaService = captchaService;
    }

    @GetMapping("/passwordExpired")
    public String displayResetPassword(Model model, @ModelAttribute ResetPasswordForm resetPasswordForm, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("isValid", true);
        return "passwordExpired";
    }

    @PostMapping("/passwordExpired")
    public String resetPassword(@Valid @ModelAttribute ResetPasswordForm resetPasswordForm,
                                BindingResult result, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        String currentUsername = session.getAttribute("username").toString();

        String response = request.getParameter("g-recaptcha-response");
        String clientIp = request.getHeader("X-FORWARDED-FOR");

        if (clientIp == null) {
            clientIp = request.getRemoteAddr();
        }

        if (!captchaService.verify(response, clientIp)) {
            model.addAttribute("isCaptchaInvalid", true);
            return "passwordExpired";
        }

        model.addAttribute("isValid", true);

        if (result.hasErrors()) {
            return "passwordExpired";
        }

        UserEntity user = userService.getUserByUsername(currentUsername);
        user.setPasswordExpired(false);
        user.setPassword(encoder.encode(resetPasswordForm.password()));
        userService.updateUser(user);
        LOG.info("user updated: " + user.getUsername());

        UserPrincipal userPrincipal = new UserPrincipal(user);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userPrincipal, auth.getCredentials(), userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return "redirect:/login";
    }
}
