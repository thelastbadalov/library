package com.company.exception.handler;

import com.company.exception.TokenNotFoundException;
import com.company.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                err-> errors.put(err.getField(),err.getDefaultMessage())
        );
return ResponseEntity.badRequest().body(errors);



    }
    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<?> handleTokenNotFoundException(TokenNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
}
}
