package com.github.wangjie.fourth.accountservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private ServiceException.ErrorType errorType;
    private String message;
    private int statusCode;
}
