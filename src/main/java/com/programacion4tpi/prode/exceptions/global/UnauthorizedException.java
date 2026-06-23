package com.programacion4tpi.prode.exceptions.global;

import com.programacion4tpi.prode.exceptions.CustomException;
import org.springframework.http.HttpStatus;

import java.net.URI;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, null, URI.create("/errors/unauthorized"), null);
    }
}
