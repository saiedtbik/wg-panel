package com.panel.wg.common.domain.valueObjects;

import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import com.panel.wg.common.domain.tools.utilities.StringUtility;
import lombok.Value;

@Value
public class NameEn {
    private String value;

    public NameEn(String v) {
        validate(v);
        this.value = v;
    }

    private void validate(String value) {
        if (StringUtility.isNullOrEmpty(value)) {
            throw new InvalidValueObjectException("NameEn can not be null or empty!.");
        }
        if (value.length() > 128) {
            throw new InvalidValueObjectException(String.format("NameEn:{0} length exceeded.", value));
        }
    }
}
