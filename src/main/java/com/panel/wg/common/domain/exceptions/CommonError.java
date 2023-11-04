package com.panel.wg.common.domain.exceptions;

public enum CommonError implements ApplicationError {

    SERVER_ERROR("server.error"),
    REQUEST_BODY_NOT_VALID("request.body.not_valid"),
    INPUT_NOT_VALID("input.not_valid"),
    INVALID_CREDENTIALS("credentials.invalid"),
    INVALID_TOKEN("token.invalid"),
    FORBIDDEN("forbidden"),
    RESOURCE_NOT_FOUND("resource.not_found"),


    FIRST_NAME_FORMAT_NOT_VALID("firstname.not_valid"),
    FIRST_NAME_LENGTH_EXCEED("firstname.length.exceed"),
    LAST_NAME_FORMAT_NOT_VALID("lastname.not_valid"),
    LAST_NAME_LENGTH_EXCEED("lastname.length.exceed"),
    NATIONAL_CODE_NOT_VALID("national_code.not_valid"),
    NATIONAL_CODE_REQUIRED("national_code.required"),
    ID_NUMBER_NOT_VALID("id_number.not_valid"),
    MOBILE_NUMBER_NOT_VALID("mobile_number.not_valid"),
    MOBILE_NUMBER_REQUIRED("mobile_number.required"),
    POSTAL_CODE_NOT_VALID("postal_code.not_valid"),
    BIRTH_DATE_NOT_VALID("birthdate.not_valid"),
    BANK_NOT_VALID("bank.not-valid"),
    DEPOSIT_NUMBER_NOT_VALID("deposit_number.not_valid"),
    DEPOSIT_NUMBER_REQUIRED("deposit_number.required"),
    DEPOSIT_NUMBER_LENGTH_EXCEED("deposit_number_length.exceed"),
    IBAN_NOT_VALID("iban.not_valid"),
    CARD_NUMBER_NOT_VALID("card_number.not_valid"),
    CARD_NUMBER_REQUIRED("card_number.required"),
    CVV2_REQUIRED("cvv2.required"),
    CVV2_NOT_VALID("cvv2.not_valid"),
    CARD_EXPIRATION_DATE_FORMAT_NOT_VALID("card_expiration_date.not_valid"),
    CARD_EXPIRATION_DATE_REQUIRED("card_expiration_date.required"),
    URL_NOT_VALID("url.not_valid"),
    URL_REQUIRED("url.required"),
    EMAIL_NOT_VALID("email.not_valid"),
    EMAIL_REQUIRED("email.required"),
    OTP_NOT_VALID("otp.not_valid"),
    OTP_REQUIRED("otp.required");


    private String key;

    CommonError(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
