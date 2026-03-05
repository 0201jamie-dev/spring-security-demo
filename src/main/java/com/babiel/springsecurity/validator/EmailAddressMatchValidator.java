package com.babiel.springsecurity.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

/**
 * validation class of both email address registration fields.
 *
 * @author Jamie Augustin
 */
public class EmailAddressMatchValidator implements ConstraintValidator<EmailAddressMatchConstraint, Object> {
    private String firstFieldName;
    private String secondFieldName;

    /**
     * sets the <code>firstFieldName</code> and <code>secondFieldName</code> variable to the <code>field1()</code> and
     * <code>field2()</code> methods of the constraint class
     *
     * @param constraintAnnotation object of the <code>EmailAddressMatchConstraint</code>
     */
    @Override
    public void initialize(EmailAddressMatchConstraint constraintAnnotation) {
        firstFieldName = constraintAnnotation.field1();
        secondFieldName = constraintAnnotation.field2();
    }

    /**
     * checks if both email address fields are identical
     *
     * @param value BeanWrapperImpl object
     * @param ctx contextual constraint validator data
     * @return boolean which indicates whether both email address fields are identical or not
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);

        Object firstField = beanWrapper.getPropertyValue(firstFieldName);
        Object secondField = beanWrapper.getPropertyValue(secondFieldName);

        return firstField.equals(secondField);
    }
}
