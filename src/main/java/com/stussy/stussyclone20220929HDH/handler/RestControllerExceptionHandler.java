package com.stussy.stussyclone20220929HDH.handler;


import com.stussy.stussyclone20220929HDH.dto.CMRespDto;
import com.stussy.stussyclone20220929HDH.exception.CustomInternalServerErrorException;
import com.stussy.stussyclone20220929HDH.exception.CustomValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationErrorException(CustomValidationException e){

        return ResponseEntity
                .badRequest()
                .body(new CMRespDto<>(-1,e.getMessage(), e.getErrorMap()));
    }

    @ExceptionHandler(CustomInternalServerErrorException.class)
    public ResponseEntity<?> internalErrorException(CustomInternalServerErrorException e){  // 500에러
        return ResponseEntity
                .internalServerError()
                .body(new CMRespDto<>(-1,e.getMessage(), null));
    }
}
