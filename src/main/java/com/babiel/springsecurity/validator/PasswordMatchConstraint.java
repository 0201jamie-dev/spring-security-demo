package com.babiel.springsecurity.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordMatchValidator.class )
@Target( { ElementType.TYPE } )
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatchConstraint {
    String message() default "The passwords need to be identical";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field1();
    String field2();
}

