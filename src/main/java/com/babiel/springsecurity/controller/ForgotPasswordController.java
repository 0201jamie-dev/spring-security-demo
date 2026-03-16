package com.babiel.springsecurity.controller;

import com.babiel.springsecurity.model.ForgotPasswordForm;
import com.babiel.springsecurity.service.EmailService;
import com.babiel.springsecurity.service.JWTService;
import com.babiel.springsecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class ForgotPasswordController {

    private final UserService userService;
    private final JWTService jwtService;
    private final EmailService emailService;

    public ForgotPasswordController(UserService userService, JWTService jwtService, EmailService emailService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    @GetMapping("forgotPassword")
    public String displayForgotPassword(@ModelAttribute ForgotPasswordForm forgotPasswordForm) {
        return "forgotPassword";
    }

    @PostMapping("forgotPassword")
    public String sendEmail(@Valid @ModelAttribute("forgotPasswordForm") ForgotPasswordForm forgotPasswordForm,
                            BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "forgotPassword";
        }

        model.addAttribute("emailSent", true);

        String baseUrl = ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        new Thread(() -> {
            try {
                if (userService.existsUserByEmailAddress(forgotPasswordForm.emailAddress())) {
                    String jwtToken = jwtService.generateToken(forgotPasswordForm.emailAddress());
                    emailService.sendEmail(forgotPasswordForm.emailAddress(), "Password Reset", baseUrl + "/resetPassword?jwt=" + jwtToken);
                }
            } catch (Exception e) {}
        }).start();

        return "forgotPassword";
    }
}
