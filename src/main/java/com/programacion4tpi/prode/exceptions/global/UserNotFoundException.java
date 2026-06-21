package com.programacion4tpi.prode.exceptions.global;

import com.programacion4tpi.prode.exceptions.CustomException;
import org.springframework.http.HttpStatus;

import java.net.URI;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, null, URI.create("/errors/not-found"), null);
    }
}