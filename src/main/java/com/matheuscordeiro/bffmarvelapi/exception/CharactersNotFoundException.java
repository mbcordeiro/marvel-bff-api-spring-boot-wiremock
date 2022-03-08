package com.matheuscordeiro.bffmarvelapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CharactersNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -571374318141836939L;

    public CharactersNotFoundException(String message) {
        super(message);
    }

    public CharactersNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}