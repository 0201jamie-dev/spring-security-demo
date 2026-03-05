package com.babiel.springsecurity.model;

import com.babiel.springsecurity.validator.EmailAddressMatchConstraint;
import com.babiel.springsecurity.validator.EmailAddressUniqueConstraint;
import com.babiel.springsecurity.validator.PasswordMatchConstraint;
import com.babiel.springsecurity.validator.UsernameUniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * POJO for the user registration page
 *
 * @author Jamie Augustin
 */
@Getter @Setter
@AllArgsConstructor
@EmailAddressMatchConstraint(field1 = "emailAddress", field2 = "emailAddressConfirmation")
@PasswordMatchConstraint(field1 = "password", field2 = "passwordConfirmation")
public class RegistrationForm {
    @NotEmpty
    @UsernameUniqueConstraint
    private String username;

    @NotEmpty
    private String realname;

    @NotEmpty
    @Email
    @EmailAddressUniqueConstraint
    private String emailAddress;

    private String emailAddressConfirmation;

    @NotNull
    @Size(min = 1, max = 50)
    private String password;

    private String passwordConfirmation;
}