package com.ofilipecode.wish_list.shared.exceptions;

public class EmailDuplicatedException extends RuntimeException {
    public EmailDuplicatedException(String message) {
        super(message);
    }
    
}
