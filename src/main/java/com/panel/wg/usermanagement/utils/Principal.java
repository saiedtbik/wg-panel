package com.panel.wg.usermanagement.utils;

import com.panel.wg.common.domain.tools.utilities.StringUtility;

public class Principal {
    public static String generateRandomAPIKey() {
        return StringUtility.random(10);
    }
    public static String generateRandomSecretKey() {
        return StringUtility.random(25);
    }
}
