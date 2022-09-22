package com.example.seminarksa_wp.model.exceptions;

public class UserEmailNotExistException extends RuntimeException{
    public UserEmailNotExistException(String email) {
        super(String.format("The user with this email %s does not exist!",email));
    }
}
