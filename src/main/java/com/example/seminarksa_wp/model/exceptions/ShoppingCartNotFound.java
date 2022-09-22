package com.example.seminarksa_wp.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFound extends RuntimeException{
    public ShoppingCartNotFound(Long cartId) {
        super(String.format("Shopping cart with id %d was not found",cartId));
    }
}
