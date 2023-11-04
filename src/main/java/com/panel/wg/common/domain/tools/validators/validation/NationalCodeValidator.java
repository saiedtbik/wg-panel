package com.panel.wg.common.domain.tools.validators.validation;

import com.panel.wg.common.domain.tools.validators.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NationalCodeValidator implements ConstraintValidator<NationalCode, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Validator.isValidNationalCode(s);
    }
}
