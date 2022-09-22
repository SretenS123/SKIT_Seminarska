package com.example.seminarksa_wp.model.exceptions;

public class InvalidTicketsException extends RuntimeException{
    public InvalidTicketsException() {
        super("There is limit of tickets you can buy!");
    }
}
