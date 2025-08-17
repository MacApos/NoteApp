package com.noteapp.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorBody(HttpStatus error, int status, List<String> message) {
    public ErrorBody(HttpStatus status, String message) {
        this(status, status.value(), List.of(message));
    }

    public ErrorBody(HttpStatus status, List<String> message) {
        this(status, status.value(), message);
    }
}
