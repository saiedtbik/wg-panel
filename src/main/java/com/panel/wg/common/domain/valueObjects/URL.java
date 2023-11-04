package com.panel.wg.common.domain.valueObjects;

import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import com.panel.wg.common.domain.tools.validators.Validator;
import lombok.Value;

@Value
public class URL {
    String value;

    public URL(String v) {
        validate(v);
        this.value = v;
    }

    private void validate(String value) {
        if (Validator.isValidUrl(value)) {
            throw new InvalidValueObjectException("URL is not valid.");
        }
    }
}
