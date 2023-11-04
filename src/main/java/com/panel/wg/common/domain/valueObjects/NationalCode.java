package com.panel.wg.common.domain.valueObjects;

import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import com.panel.wg.common.domain.tools.validators.Validator;
import lombok.Value;

@Value
public class NationalCode {
    private String value;

    public NationalCode(String v) {
        validate(v);
        this.value = v;
    }

    private void validate(String value) {
        if (!Validator.isValidNationalCode(value)) {
            throw new InvalidValueObjectException(String.format("NationalCode is not valid.", value));
        }
    }

}
