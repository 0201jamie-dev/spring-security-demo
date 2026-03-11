package com.babiel.springsecurity.controller;

import com.babiel.springsecurity.model.ResetPasswordForm;
import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.service.JWTService;
import com.babiel.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class ResetPasswordController {
    private final JWTService jwtService;
    private final UserService userService;
    private final PasswordEncoder encoder;

    public ResetPasswordController(JWTService jwtService, UserService userService, PasswordEncoder encoder) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/resetPassword")
    public String displayResetPassword(@RequestParam String jwt, Model model, @ModelAttribute ResetPasswordForm resetPasswordForm) throws UnsupportedEncodingException {
        if (!jwtService.isTokenValid(jwt)) {
            model.addAttribute("isValid", false);
            return "resetPassword";
        }
        model.addAttribute("jwt", jwt);
        System.out.println("Token is valid");
        model.addAttribute("isValid", true);
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@Valid @ModelAttribute ResetPasswordForm resetPasswordForm,
                                BindingResult result, Model model) throws UnsupportedEncodingException {
        model.addAttribute("isValid", true);
        if (result.hasErrors()) {
            return "resetPassword";
        }

        if (!jwtService.isTokenValid(resetPasswordForm.token())) {
            return "resetPassword";
        }
        UserEntity user = userService.getUserByEmailAddress(jwtService.getSubject(resetPasswordForm.token()));
        user.setPassword(encoder.encode(resetPasswordForm.password()));
        userService.updateUser(user);
        // TODO: Richtiges Logging und .toString() Methode
        System.out.println("user updated: " + user.toString());
        return "redirect:/login";
    }
}
