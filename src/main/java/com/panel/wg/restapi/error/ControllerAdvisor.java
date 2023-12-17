package com.panel.wg.restapi.error;

import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.common.domain.exceptions.CommonError;
import com.panel.wg.common.domain.exceptions.InvalidOperationException;
import com.panel.wg.common.domain.exceptions.InvalidValueObjectException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestControllerAdvice
public class ControllerAdvisor {
    private final MessageSource messageSource;

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorResponse> handleConversionFailedException(RuntimeException ex) {

        log.error("Error occurred :{}", ex);
        ErrorResponse response = ErrorResponse.builder()
                .status(CommonError.INPUT_NOT_VALID.getKey())
                .message(messageSource.getMessage(CommonError.INPUT_NOT_VALID.getKey(), null, Locale.getDefault()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(HttpMessageNotReadableException ex, WebRequest request) {

        log.error("Error occurred :{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(CommonError.REQUEST_BODY_NOT_VALID.getKey())
                .message(messageSource.getMessage(CommonError.REQUEST_BODY_NOT_VALID.getKey(), null, Locale.getDefault()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        log.error("Error occurred :{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(CommonError.INPUT_NOT_VALID.getKey())
                .message(messageSource.getMessage(CommonError.INPUT_NOT_VALID.getKey(), null, Locale.getDefault()))
                .build();

        ex.getBindingResult().getFieldErrors().stream()
                .forEach((e -> response.addValidationError(e.getField(), e.getDefaultMessage())));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessRuleViolationException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleViolationException(BusinessRuleViolationException ex, WebRequest request) {

        log.error("Error occurred :{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getError().getKey())
                .message(messageSource.getMessage(ex.getError().getKey(), null, Locale.UK))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





    @ExceptionHandler({InvalidValueObjectException.class})
    public ResponseEntity<ErrorResponse> invalidValueObjectException(InvalidValueObjectException ex, WebRequest request) {

        log.error("Error occurred :{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getError().getKey())
                .message(messageSource.getMessage(ex.getError().getKey(), null, Locale.getDefault()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({InvalidOperationException.class})
    public ResponseEntity<ErrorResponse> invalidOperationException(InvalidOperationException ex, WebRequest request) {

        log.error("Error occurred :{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getError().getKey())
                .message(messageSource.getMessage(ex.getError().getKey(), null, Locale.getDefault()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialException(Exception ex, WebRequest request) {

        log.error("Error occurred :{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(CommonError.INVALID_CREDENTIALS.getKey())
                .message(messageSource.getMessage(CommonError.INVALID_CREDENTIALS.getKey(), null, Locale.getDefault()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException(Exception ex, WebRequest request) {

        log.error("Error occurred :{}", ex);

        ErrorResponse response = ErrorResponse.builder()
                .status(CommonError.SERVER_ERROR.getKey())
                .message(messageSource.getMessage(CommonError.SERVER_ERROR.getKey(), null, Locale.getDefault()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private String getRootCauseClassName(Exception e) {
        Throwable rootCause = e;
        while (e.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = e.getCause();
        }
        return rootCause.getClass().getName();
    }

}
