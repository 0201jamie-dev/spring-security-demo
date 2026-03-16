package com.babiel.springsecurity.model;

import com.babiel.springsecurity.validator.PasswordMatchConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordMatchConstraint(field1 = "password", field2 = "passwordConfirmation")
public record ResetPasswordForm(
        @Size(min = 1)
        @NotNull
        String password,
        String passwordConfirmation,
        String token
) {
}
