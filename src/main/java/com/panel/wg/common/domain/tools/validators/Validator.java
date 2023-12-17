package com.panel.wg.common.domain.tools.validators;

import com.github.mfathi91.time.PersianDate;
import org.apache.commons.validator.routines.UrlValidator;

import java.math.BigInteger;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * A utility validator class.
 *
 * @author Mohammadreza Rahimian
 */
public final class Validator {
    private static final BigInteger NINETY_SEVEN = new BigInteger("97");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter DATE_TIME_FORMATTER_With_LINE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter YEAR_MONTH_DAY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

    /**
     * Represents the number pattern.
     */
    public static final String NUMBER_PATTERN = "\\d+";

    /**
     * Represents the national code pattern.
     */
    public static final String NATIONAL_CODE_PATTERN = "^(\\d{10})$";

    /**
     * Represents the legal national code pattern.
     */
    public static final String LEGAL_NATIONAL_CODE_PATTERN = "^(\\d{11})$";

    /**
     * The list of black list national codes.
     */
    private static final Set<String> NATIONAL_CODE_BLACKLIST = Set.of("0000000000", "1111111111", "2222222222",
            "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999",
            "1234567890", "0123456789");

    /**
     * Represents the persian birth date pattern.
     */
    public static final String PERSIAN_BIRTH_DATE_PATTERN = "1[2-5]\\d{2}(?:0[1-6](?:[0-2]\\d|3[0-1])|(?:0[7-9]|1[0-2])(?:[0-2]\\d|30))";

    /**
     * Represents the mobile number pattern.
     */
    public static final String MOBILE_NUMBER_PATTERN = "^(?:(?:\\+|00|)98|0)9\\d{9}$";

    /**
     * Represents the iban pattern.
     */
    public static final String IBAN_PATTERN = "IR[0-9]{24}";

    /**
     * Represents the card number pattern.
     */
    public static final String CARD_NUMBER_PATTERN = "^(\\d{16})$";

    /**
     * Represents the deposit number pattern.
     */
    public static final String DEPOSIT_NUMBER_PATTERN = "^(?:[\\d|\\-]{3,30}|[\\d|.]{3,30}|[\\d|/]{3,30})$";

    /**
     * Represents the dash based deposit number pattern.
     */
    public static final String DASH_BASED_DEPOSIT_NUMBER_PATTERN = "^([\\d|\\-]{3,30})$";

    /**
     * Represents the digit based deposit number pattern.
     */
    public static final String DIGIT_BASED_DEPOSIT_NUMBER_PATTERN = "^(\\d{3,30})$";

    /**
     * Represents the dot based deposit number pattern.
     */
    public static final String DOT_BASED_DEPOSIT_NUMBER_PATTERN = "^([\\d|.]{3,30})$";

    /**
     * Represents the slash based deposit number pattern.
     */
    public static final String SLASH_BASED_DEPOSIT_NUMBER_PATTERN = "^([\\d|/]{3,30})$";

    /**
     * Represents the ip address pattern.
     */
    public static final String IP_ADDRESS_PATTERN = "^(\\d{1,3}\\.){3}\\d{1,3}$";

    /**
     * Represents the postal code pattern.
     */
    public static final String POSTAL_CODE_PATTERN = "^([1-9]\\d{9})$";

    /**
     * The list of black list postal codes.
     */
    private static final Set<String> POSTAL_CODE_BLACKLIST = Set.of("0000000000", "1111111111", "2222222222",
            "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999");

    /**
     * Represents the yearMonth pattern.
     */
    public static final String YEAR_MONTH_PATTERN = "[0-1]\\d(?:0[1-6]|(?:0[7-9]|1[0-2]))";

    /**
     * Represents the yearMonthDay pattern.
     */
    public static final String YEAR_MONTH_DAY_PATTERN = "[0-1]\\d(?:0[1-6](?:[0-2]\\d|3[0-1])|(?:0[7-9]|1[0-2])(?:[0-2]\\d|30))";

    /**
     * Checks whether a provided number is purely digit or not.
     *
     * @param number string representation of number
     * @return true if number is purely digit otherwise false
     */
    public static boolean isValidNumber(String number) {
        return number.matches(NUMBER_PATTERN);
    }

    /**
     * Checks whether a provided national code is valid or not.
     *
     * @param nationalCode string representation of national code
     * @return true if national code is valid otherwise false
     */
    public static boolean isValidNationalCode(String nationalCode) {
        if (NATIONAL_CODE_BLACKLIST.contains(nationalCode))
            return false;

        if (nationalCode.matches(NATIONAL_CODE_PATTERN)) {
            int check = Integer.valueOf(nationalCode.substring(nationalCode.length() - 1), nationalCode.length());
            int sum = IntStream.range(0, 9).map(n -> Integer.parseInt(nationalCode.substring(n, n + 1)) * (10 - n)).sum() % 11;
            return sum < 2 && check == sum || sum >= 2 && check + sum == 11;
        }

        return false;
    }

    /**
     * Checks whether a provided legal national code is valid or not.
     *
     * @param nationalCode string representation of legal national code
     * @return true if legal national code is valid otherwise false
     */
    public static boolean isValidLegalNationalCode(String nationalCode) {
        if (!nationalCode.matches(LEGAL_NATIONAL_CODE_PATTERN)) return false;

        var nationalCodeWithoutControlDigit = nationalCode.substring(0, nationalCode.length() - 1);
        var controlDigit = nationalCode.substring(nationalCode.length() - 1);
        var decimal = Integer.parseInt(nationalCode.substring(nationalCode.length() - 2, nationalCode.length() - 1)) + 2;
        var multiplier = new int[]{29, 27, 23, 19, 17, 29, 27, 23, 19, 17};
        var sum = 0;

        var chars = nationalCodeWithoutControlDigit.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sum += (Integer.parseInt("" + chars[i]) + decimal) * multiplier[i];
        }

        var modBy11 = sum % 11;
        if (modBy11 == 10) modBy11 = 0;

        return modBy11 == Integer.parseInt(controlDigit);
    }

    /**
     * Checks whether a provided persian birth date is valid or not.
     *
     * @param birthDate birth date
     * @return true if persian birth date is valid otherwise false
     */
    public static boolean isValidPersianBirthDate(String birthDate) {
        if (!birthDate.matches(PERSIAN_BIRTH_DATE_PATTERN))
            return false;

        try {
            return PersianDate.parse(birthDate, DATE_TIME_FORMATTER).isBefore(PersianDate.now());
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Checks whether a provided mobile number is valid or not.
     *
     * @param mobileNumber string representation of mobile number
     * @return true if mobile number is valid otherwise false
     */
    public static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.trim().matches(MOBILE_NUMBER_PATTERN);
    }

    /**
     * Checks whether a provided deposit number is valid or not.
     *
     * @param depositNumber string representation of deposit number
     * @return true if deposit number is valid otherwise false
     */
    public static boolean isValidDepositNumber(String depositNumber) {
        return depositNumber.matches(DEPOSIT_NUMBER_PATTERN);
    }

    /**
     * Checks validity of an IBAN by reformatting it and compute its modulus with "97". The true result should be "1".
     *
     * @param iban iban value
     * @return true if iban is valid otherwise false
     */
    public static boolean isValidIban(String iban) {
        return iban.matches(IBAN_PATTERN) && new BigInteger(regulator(iban)).mod(NINETY_SEVEN).intValue() == 1;
    }

    /**
     * Checks whether a provided card number is valid or not.
     *
     * @param cardNumber string representation of card number
     * @return true if card number is valid otherwise false
     */
    public static boolean isValidCardNumber(String cardNumber) {
        if (!cardNumber.matches(CARD_NUMBER_PATTERN))
            return false;

        int sum = 0;

        var digits = cardNumber.split("");
        for (int i = 0; i < digits.length; i++) {
            if (i % 2 == 0) {
                var temp = Integer.parseInt(digits[i]) * 2;
                if (temp > 9)
                    temp -= 9;

                sum += temp;
            } else {
                sum += Integer.parseInt(digits[i]);
            }
        }

        return sum % 10 == 0;
    }

    /**
     * Checks whether a provided ip address is valid or not.
     *
     * @param ipAddress string representation of ip address
     * @return true if ip address is valid otherwise false
     */
    public static boolean isValidIpAddress(String ipAddress) {
        return ipAddress.matches(IP_ADDRESS_PATTERN);
    }

    /**
     * Checks whether a provided postal code is valid or not.
     *
     * @param postalCode string representation of postal code
     * @return true if postal code is valid otherwise false
     */
    public static boolean isValidPostalCode(String postalCode) {
        if (POSTAL_CODE_BLACKLIST.contains(postalCode))
            return false;

        return postalCode.matches(POSTAL_CODE_PATTERN);
    }

    /**
     * Checks whether a provided yearMonth is valid or not.
     *
     * @param yearMonth string representation of ip address
     * @return true if yearMonth is valid otherwise false
     */
    public static boolean isValidYearMonth(String yearMonth) {
        if (!yearMonth.matches(YEAR_MONTH_PATTERN))
            return false;

        try {
            var nowDay = String.valueOf(PersianDate.now().getDayOfMonth());
            nowDay = nowDay.length() < 2 ? "0".concat(nowDay) : nowDay;

            var formatted = PersianDate.parse(yearMonth.concat(nowDay), YEAR_MONTH_DAY_DATE_TIME_FORMATTER);
            var currentDate = PersianDate.now();

            return formatted.isEqual(currentDate) || formatted.isBefore(currentDate);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Checks whether a provided yearMonthDay is valid or not.
     *
     * @param yearMonthDay string representation of ip address
     * @return true if yearMonthDay is valid otherwise false
     */
    public static boolean isValidYearMonthDay(String yearMonthDay) {
        if (!yearMonthDay.matches(YEAR_MONTH_DAY_PATTERN))
            return false;

        try {
            var formatted = PersianDate.parse(yearMonthDay, YEAR_MONTH_DAY_DATE_TIME_FORMATTER);
            return true;
//            var currentDate = PersianDate.now();
//            return formatted.isEqual(currentDate) || formatted.isBefore(currentDate);

        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Checks validation of a provided value according to provided regexes.
     *
     * @param regexes provided array of regexes
     * @param value   provided value
     * @return true if provided value matches with one of provided regexes
     */
    public static boolean isValid(String[] regexes, String value) {
        for (var regex : regexes) {
            var isMatched = value.matches(regex);

            if (isMatched) return true;
        }

        return false;
    }

    /**
     * Repositions first four characters to the end of iban, then maps non digit parts into its corresponded value.
     *
     * @param iban iban value
     * @return reformatted iban representation
     */
    private static String regulator(String iban) {
        var substring = iban.replaceFirst("I", "18").replaceFirst("R", "27").substring(0, 6);
        return new StringBuilder(iban).append(substring).delete(0, 4).toString();
    }

    /**
     * Checks whether a provided url is valid or not.
     *
     * @param url string representation of url
     * @return true if url is valid otherwise false
     */
    public static boolean isValidUrl(final String url) {
        return UrlValidator.getInstance().isValid(url);
    }

    /**
     * Converts provided uuid into {@link UUID}. In null argument it will return null itself.
     *
     * @param uuid provided uuid
     * @return uuid wrapped into {@link UUID}
     */
    public static UUID uuidOrNull(String uuid) {
        return uuid == null ? null : UUID.fromString(uuid);
    }

}
