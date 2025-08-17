package com.noteapp.exception;

public class EntityNotFound extends RuntimeException {

    public EntityNotFound(String name, Long id) {
        super(String.format("%s with id %s was not found.",name, id ) );
    }
}
