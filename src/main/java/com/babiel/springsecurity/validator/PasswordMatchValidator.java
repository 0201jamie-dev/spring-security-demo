package com.babiel.springsecurity.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

/**
 * validation class of the password and password confirmation field.
 * Checks if they are identical
 *
 * @author Jamie Augustin
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatchConstraint, Object> {
    private String firstFieldName;
    private String secondFieldName;

    /**
     * sets the variables <code>firstFieldName</code> and <code>secondFieldName</code>
     * to the <code>field1()</code> and <code>field2()</code> methods of the constraint class
     *
     * @param constraintAnnotation object of the <code>PasswordMatchConstraint</code>
     */
    @Override
    public void initialize(PasswordMatchConstraint constraintAnnotation) {
        firstFieldName = constraintAnnotation.field1();
        secondFieldName = constraintAnnotation.field2();
    }

    /**
     * checks if both password fields are identical
     *
     * @param value BeanWrapperImpl object
     * @param ctx contextual constraint validator data
     * @return boolean which indicates whether both passwords fields are identical or not
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);

        Object firstField = beanWrapper.getPropertyValue(firstFieldName);
        Object secondField = beanWrapper.getPropertyValue(secondFieldName);

        return firstField.equals(secondField);
    }
}
