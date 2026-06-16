package com.programacion4tpi.prode.exceptions.jwt;

import com.programacion4tpi.prode.exceptions.CustomException;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;

public class UserAlreadyExistsException extends CustomException {
    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT, null, URI.create("/errors/conflict"), null);
    }

    public UserAlreadyExistsException(String message, List<String> errors) {
        super(message, HttpStatus.CONFLICT, errors, URI.create("/errors/conflict"), null);
    }
}
