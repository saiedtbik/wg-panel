package com.panel.wg.common.domain.tools.utilities;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Objects;

public class StringUtility {
    public static final String EMPTY = "";

    public static boolean isNullOrEmpty(String str) {
        return Objects.isNull(str) || str.isEmpty();
    }

    private static final SecureRandom random = new SecureRandom(
            String.valueOf((System.currentTimeMillis())).getBytes());

    public static String random(int size) {
        var key = new byte[size];
        random.nextBytes(key);
        var generated = Base64.getUrlEncoder().encodeToString(key).replaceAll("\\W", "");
        return generated.length() > size ? generated.substring(0, size) : generated;
    }
}
