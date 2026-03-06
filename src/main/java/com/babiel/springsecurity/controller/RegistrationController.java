package com.babiel.springsecurity.controller;

import com.babiel.springsecurity.model.RegistrationForm;
import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

/**
 * Dies ist ein Controller der das <code>RegistrationForm</code> organisiert
 *
 * @author Jamie Augustin
 */
@Controller
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public RegistrationController(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            String emailAddressErrorMessage = bindingResult.getGlobalErrors()
                    .stream()
                    .filter(e -> "EmailAddressMatchConstraint"
                            .equals(e.getCode()))
                    .map(e -> e.getDefaultMessage())
                    .findFirst()
                    .orElse(null);
            model.addAttribute("emailAddressConstraint", emailAddressErrorMessage);

            String passwordErrorMessage = bindingResult.getGlobalErrors()
                    .stream()
                    .filter(e -> "PasswordMatchConstraint"
                            .equals(e.getCode()))
                    .map(e -> e.getDefaultMessage())
                    .findFirst()
                    .orElse(null);
            model.addAttribute("passwordConstraint", passwordErrorMessage);
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
