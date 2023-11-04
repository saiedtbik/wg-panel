package com.panel.wg.common.domain.valueObjects;

import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import com.panel.wg.common.domain.tools.utilities.StringUtility;
import com.panel.wg.common.domain.tools.validators.Validator;
import lombok.Value;

@Value
public class PostalCode {
    private String value;

    public PostalCode(String v) {
        validate(v);
        this.value = v;
    }

    private void validate(String value) {
        if (StringUtility.isNullOrEmpty(value)) {
            throw new InvalidValueObjectException("PostalCode value can not be null or empty!");
        }
        if (!Validator.isValidPostalCode(value)) {
            throw new InvalidValueObjectException(String.format("PostalCode : {0} is not valid!", value));
        }
    }

}

