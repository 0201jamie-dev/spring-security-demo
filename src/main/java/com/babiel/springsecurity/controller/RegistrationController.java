package com.babiel.springsecurity.controller;

import com.babiel.springsecurity.model.RegistrationForm;
import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Dies ist ein Controller der das <code>RegistrationForm</code> organisiert
 *
 * @author Jamie Augustin
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult bindingResult) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registrationForm.getUsername());
        newUser.setRealname(registrationForm.getRealname());
        newUser.setEmailAddress(registrationForm.getEmailAddress());
        newUser.setPassword(encoder.encode(registrationForm.getPassword()));

        userService.createUser(newUser);
        return "redirect:/login";
    }
}
