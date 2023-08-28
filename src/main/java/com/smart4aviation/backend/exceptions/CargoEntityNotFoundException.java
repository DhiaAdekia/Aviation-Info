package com.smart4aviation.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CargoEntityNotFoundException extends RuntimeException {
    public CargoEntityNotFoundException(String message) {
        super(message);
    }
}
