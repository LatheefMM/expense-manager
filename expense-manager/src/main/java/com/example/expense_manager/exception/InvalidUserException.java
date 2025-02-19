package com.example.expense_manager.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.FORBIDDEN)

public class InvalidUserException extends  RuntimeException {
    private  final  String entity;

    public InvalidUserException(String entity, Long userid) {
        super("Invalid"+entity);
        this.entity = entity;
    }
}
