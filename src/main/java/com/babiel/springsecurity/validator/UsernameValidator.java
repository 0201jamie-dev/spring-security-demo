package com.babiel.springsecurity.validator;

import com.babiel.springsecurity.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * This class checks if the entered username already exists
 *
 * @author Jamie Augustin
 */
public class UsernameValidator implements ConstraintValidator<UsernameUniqueConstraint, String> {
    private final UserService userService;

    /**
     * constructor injection of the <code>UserService</code>
     *
     * @param userService service which handles requests for the user repository
     */
    public UsernameValidator(UserService userService) {
        this.userService = userService;
    }

    /**
     * checks whether the username already exists in the database or not
     *
     * @param username string that got entered by the user in the username field
     * @param ctx contextual constraint validator data
     * @return boolean which returns true if the username is not used yet
     */
    @Override
    public boolean isValid(String username, ConstraintValidatorContext ctx) {
        return !userService.existsUserByUsername(username);
    }
}
