package com.example.seminarksa_wp.model.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super(String.format("User with this email already exist!",email));
    }
}
