package com.panel.wg.common.domain.tools.validators.validation;

import com.panel.wg.common.domain.tools.validators.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PostalCodeValidator implements ConstraintValidator<PostalCode, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Validator.isValidPostalCode(s);
    }
}
