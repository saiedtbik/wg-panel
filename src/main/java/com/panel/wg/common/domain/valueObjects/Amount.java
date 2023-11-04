package com.panel.wg.common.domain.valueObjects;

import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import lombok.Value;

import java.util.Objects;

@Value
public class Amount {
    Rial value;

    public Amount(Rial rial) {
        validate(rial);
        this.value = rial;
    }

    private void validate(Rial rial) {
        if (rial.isNegative()) {
            throw new InvalidValueObjectException("Amount can not be negative");
        }
    }

    public static Amount fromString(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new InvalidValueObjectException("Value can't be null to convert from String to Amount");
        }
        return new Amount(Rial.fromString(value));
    }

    public static Amount fromLong(Long value) {
        Objects.requireNonNullElseGet(value, () -> {
            throw new InvalidValueObjectException("Value can't to be null to convert from Long to Amount");
        });
        return new Amount(Rial.fromLong(value));
    }



}
