package com.github.wangjie.fourth.accountservice.exception;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


//    @ExceptionHandler(ResourceNotFoundException.class)
//    ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
//        val errorResponse = ErrorResponse.builder()
//                .statusCode(HttpStatus.NOT_FOUND.value())
//                .message(ex.getMessage())
//                .code("USER_NOT_FOUND")
//                .errorType(ServiceException.ErrorType.Client)
//                .build();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//    }

    // 这里其实还不如在德易的俩种异常处理逻辑
    @ExceptionHandler(InvalidParameterException.class)
    ResponseEntity<?> handleInvalidParameterException(InvalidParameterException ex) {
        val errorResponse = ErrorResponse.builder()
                .statusCode(ex.getStatusCode())
                .message(ex.getMessage())
                .code(ex.getErrorCode())
                .errorType(ex.getErrorType())
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

}
