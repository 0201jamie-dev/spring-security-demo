package com.babiel.springsecurity.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UsernameValidator.class )
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUniqueConstraint {
    String message() default "This username is already in use";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
