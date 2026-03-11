package com.babiel.springsecurity.model;

import jakarta.validation.constraints.Email;

public record ForgotPasswordForm(@Email String emailAddress) {
}
