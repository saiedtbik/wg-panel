package com.panel.wg.common.domain.tools.validators;


import com.panel.wg.common.domain.tools.utilities.StringUtility;

public class PersianNameValidator {
    public static boolean isValid(String name) {
        return !StringUtility.isNullOrEmpty(name);
    }
}
