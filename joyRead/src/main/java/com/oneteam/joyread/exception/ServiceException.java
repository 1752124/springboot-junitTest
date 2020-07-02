package com.oneteam.joyread.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException implements Serializable {

    private String msg;
    private HttpStatus status;

    public ServiceException() {
        this(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ServiceException(String msg) {
        this(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ServiceException(HttpStatus status) {
        this("no message", status);
    }

    public ServiceException(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
    }
}

