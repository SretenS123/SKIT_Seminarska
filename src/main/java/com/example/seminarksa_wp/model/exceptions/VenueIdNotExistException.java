package com.example.seminarksa_wp.model.exceptions;

public class VenueIdNotExistException extends RuntimeException{
    public VenueIdNotExistException(Long id) {
        super(String.format("The venue with this id %d does not exist!",id));
    }
}
