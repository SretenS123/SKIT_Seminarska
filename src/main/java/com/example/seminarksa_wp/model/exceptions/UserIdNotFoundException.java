package com.example.seminarksa_wp.model.exceptions;

public class UserIdNotFoundException extends RuntimeException{
    public UserIdNotFoundException(Long id) {
        super(String.format("The user with this id %d does not exist!",id));
    }
}
