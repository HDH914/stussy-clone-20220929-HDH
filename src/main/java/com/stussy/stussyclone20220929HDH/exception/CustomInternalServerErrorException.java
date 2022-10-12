package com.stussy.stussyclone20220929HDH.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class CustomInternalServerErrorException extends RuntimeException{
    public CustomInternalServerErrorException(String message){
        super(message);
    }
}
