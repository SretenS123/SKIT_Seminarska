package com.example.seminarksa_wp.model.exceptions;

public class TicketIdNotFoundException extends RuntimeException{

    public TicketIdNotFoundException(Long id) {
        super(String.format("The ticket with this id %d does not exist!",id));
    }
}
