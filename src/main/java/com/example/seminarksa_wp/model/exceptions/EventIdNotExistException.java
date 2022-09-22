package com.example.seminarksa_wp.model.exceptions;

public class EventIdNotExistException extends RuntimeException{
    public EventIdNotExistException(Long id) {
        super(String.format("The event with this id %d does not exist!",id));
    }
}
