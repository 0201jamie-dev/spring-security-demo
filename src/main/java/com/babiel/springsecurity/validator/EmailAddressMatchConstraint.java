package com.babiel.springsecurity.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EmailAddressMatchValidator.class )
@Target( { ElementType.TYPE } )
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailAddressMatchConstraint {
    String message() default "The email addresses need to be identical";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field1();
    String field2();
}

