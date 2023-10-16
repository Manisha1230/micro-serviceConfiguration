package com.user.service.UserServiceMicroservices.exception;

import com.user.service.UserServiceMicroservices.payload.ApiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice// using this annotation to give advice Exception globally to handle or we can say centralized exception to handle all over the project
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
   public ApiResponse handlerResourceNotFoundException(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return response;

   }
}
