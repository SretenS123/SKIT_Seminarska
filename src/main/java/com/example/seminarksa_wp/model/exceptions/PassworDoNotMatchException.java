package com.example.seminarksa_wp.model.exceptions;

public class PassworDoNotMatchException extends RuntimeException{
    public PassworDoNotMatchException()
    {
        super("Passwords dont match eachother!");
    }
}
