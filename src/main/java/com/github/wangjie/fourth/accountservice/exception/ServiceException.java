package com.github.wangjie.fourth.accountservice.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException{

    private int statusCode;

    private String errorCode;

    private ErrorType errorType;



    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    public ServiceException(String message) {
        super(message);
    }
}
