package com.babiel.springsecurity.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailAddressValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddressUniqueConstraint {
    String message() default "Email address already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
