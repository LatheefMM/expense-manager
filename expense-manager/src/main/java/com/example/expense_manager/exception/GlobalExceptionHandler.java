package com.example.expense_manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?>handleEntityNotFoundException(EntityNotFoundException ex){
        Map<String,Object> errordetails = new HashMap<>();
        errordetails.put("Message",ex.getMessage());

        return  new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidUserException.class)
    public  ResponseEntity<?>handleinvaliduserexception(InvalidUserException ex){
        Map<String,Object>errordetails = new HashMap<>();
        errordetails.put("Message",ex.getMessage());
        return  new ResponseEntity<>(errordetails, HttpStatus.FORBIDDEN);
    }
}
