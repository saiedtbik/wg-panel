package com.panel.wg.common.domain.valueObjects;

import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import com.panel.wg.common.domain.tools.validators.Validator;
import lombok.Value;

@Value
public class MobileNumber {
    private String value;

    public MobileNumber(String v) {
        validate(v);
        this.value = v;
    }

    private void validate(String value) {
        if (!Validator.isValidMobileNumber(value)) {
            throw new InvalidValueObjectException(String.format("MobileNumber: {0} is not valid", value));
        }
    }

}
