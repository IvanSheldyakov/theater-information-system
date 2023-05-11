package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.exception.ConflictException;
import com.db.theaterinformationsystem.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFound(DataNotFoundException dataNotFoundException) {
        return new ResponseEntity<>(dataNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflict(ConflictException conflictException) {
        return new ResponseEntity<>(conflictException.getLocalizedMessage(), HttpStatus.CONFLICT);
    }
}
