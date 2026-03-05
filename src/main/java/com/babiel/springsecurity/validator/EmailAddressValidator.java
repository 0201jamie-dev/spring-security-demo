package com.babiel.springsecurity.validator;

import com.babiel.springsecurity.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class EmailAddressValidator implements ConstraintValidator<EmailAddressUniqueConstraint, String> {

    private final UserService userService;

    public EmailAddressValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailAddressUniqueConstraint constraintAnnotation) {}

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        return !userService.existsUserByEmailAddress(email);
    }
}
