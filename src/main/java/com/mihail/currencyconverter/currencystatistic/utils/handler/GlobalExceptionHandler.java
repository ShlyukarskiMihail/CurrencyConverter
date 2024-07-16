package com.mihail.currencyconverter.currencystatistic.utils.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleException(final DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(CONFLICT)
                .body(
                        ExceptionResponse.builder()
                                .errorCode(BusinessErrorCodes.DUPLICATED_REQUEST.getCode())
                                .errorDescription(BusinessErrorCodes.DUPLICATED_REQUEST.getDescription())
                                .error(ex.getMessage())
                                .build()
                );
    }
}