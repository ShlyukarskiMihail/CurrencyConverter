package com.mihail.currencyconverter.currencystatistic.utils.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum BusinessErrorCodes {

    DUPLICATED_REQUEST(409, HttpStatus.CONFLICT, "Duplicated Request");

    @Getter
    private final int code;

    @Getter
    private final String description;

    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(final int code, final HttpStatus httpStatus, final String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
