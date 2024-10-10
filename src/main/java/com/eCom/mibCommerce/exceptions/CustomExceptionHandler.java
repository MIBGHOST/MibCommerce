package com.eCom.mibCommerce.exceptions;

import com.eCom.mibCommerce.model.CustomErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request){
        CustomErrorResponseDto errorResponseDto = new CustomErrorResponseDto(ex.getMessage(), "Product doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
