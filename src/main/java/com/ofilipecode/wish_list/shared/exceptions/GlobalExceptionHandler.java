package com.ofilipecode.wish_list.shared.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<?> handleDuplicatedEmailException(EmailDuplicatedException exception) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", exception.getMessage()));    
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialException exception) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("error", exception.getMessage()));
    }
}
