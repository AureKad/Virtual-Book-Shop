package com.kadzys.VBooks.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
