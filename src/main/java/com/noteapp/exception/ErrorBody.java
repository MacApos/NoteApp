package com.noteapp.exception;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public record ErrorBody(HttpStatus error, int status, List<String> message) {
    public ErrorBody(HttpStatus error, String message) {
        this(error, List.of(message));
    }

    public ErrorBody(HttpStatus error, List<String> message) {
        this(error, error.value(), message);
    }
}
