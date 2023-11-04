package com.panel.wg.common.domain.tools.validators.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({ FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = URLValidator.class)
public @interface URL {
    String message() default "{url.not_valid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
