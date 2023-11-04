package com.panel.wg.common.domain.valueObjects;

import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import lombok.Value;
import java.util.Objects;

@Value
public class Rial {
    private Long value;

    public Rial(Long v) {
        validate(v);
        this.value = v;
    }

    private void validate(Long value) {
        Objects.requireNonNullElseGet(value, () -> {
            throw new InvalidValueObjectException("Rial's amount can not be null");
        });
    }

    public Rial add(Rial rial) {
        return new Rial(this.value + rial.value);
    }

    public Rial subtract(Rial rial) {
        return new Rial(this.value - rial.value);
    }

    public boolean isNegative() {
        return this.value < 0;
    }

    public static Rial fromString(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            throw new InvalidValueObjectException("Value can't be null to convert from String to Rial");
        }
        return new Rial(Long.valueOf(value));
    }

    public static Rial fromLong(Long value) {
        Objects.requireNonNullElseGet(value, () -> {
            throw new InvalidValueObjectException("Value can't to be null to convert from Long to Rial");
        });
        return new Rial(value);
    }

}
