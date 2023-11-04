package com.panel.wg.client.domain.exceptions;

import com.panel.wg.common.domain.exceptions.ApplicationError;

public enum ClientError implements ApplicationError {


    //----------------------------------------------- Input Validation Error Code -------------------------------------------
    MANDATE_REFERENCE_NUM_REQUIRED("mandate.reference.required"),
    MANDATE_REFERENCE_NUM_LENGTH_EXCEED("mandate.reference.length.not_valid"),
    MANDATE_REFERENCE_NUM_NOT_VALID("mandate.reference.not_valid"),
    MANDATE_FREQUENCY_REQUIRED("mandate.frequency.required"),
    MANDATE_FREQUENCY_NOT_VALID("mandate.frequency.not_valid"),
    MANDATE_PERIOD_REQUIRED("mandate.period.required"),
    MANDATE_PERIOD_NOT_VALID("mandate.period.not_valid"),
    MANDATE_PERIOD_UNIT_NOT_VALID("mandate.period_unit.not_valid"),
    MANDATE_MAX_AMOUNT_REQUIRED("mandate.max_amount.required"),
    MANDATE_MAX_AMOUNT_NOT_VALID("mandate.max_amount.not_valid"),
    MANDATE_START_DATE_REQUIRED("mandate.start_date.required"),
    MANDATE_START_DATE_FORMAT_NOT_VALID("mandate.start_date.format.not_valid"),
    MANDATE_EXPIRATION_DATE_REQUIRED("mandate.expiration-date.required"),
    MANDATE_EXPIRATION_DATE_FORMAT_NOT_VALID("mandate.expiration_date.format.not_valid"),
    MANDATE_REASON_LENGTH_EXCEED("mandate.reason.length.exceed"),
    CREDITOR_IDENTIFIER_REQUIRED("mandate.creditor_identifier.required"),
    MANDATE_REVOKE_ORIGINATOR_REQUIRED("mandate.revoked_originator.required"),

    /*---------------------------------------------- Business Rule Validation Error ------------------------------*/

    CLIENT_NO_TRAFFIC("client.no_traffic"),
    CLIENT_NOT_EXIST("client.not.exist")

    ;

    private String key;

    ClientError(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}