package com.programacion4tpi.prode.exceptions.global;

import com.programacion4tpi.prode.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import java.net.URI;


public class PartidoNotFoundException extends CustomException {
    public PartidoNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, null, URI.create("/errors/not-found"), null);
    }
}
