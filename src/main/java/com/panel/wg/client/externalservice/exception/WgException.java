package com.panel.wg.client.externalservice.exception;

import com.panel.wg.common.domain.exceptions.BaseException;
import com.panel.wg.common.domain.exceptions.CommonError;
import org.springframework.http.HttpStatusCode;

public class WgException extends BaseException {

    private String serviceName;
    private HttpStatusCode statusCode;
    private String error;


    public WgException(
            String serviceName,
            HttpStatusCode statusCode,
            String error) {

        super(CommonError.SERVER_ERROR);
        this.serviceName = serviceName;
        this.statusCode = statusCode;
        this.error = error;
    }

}