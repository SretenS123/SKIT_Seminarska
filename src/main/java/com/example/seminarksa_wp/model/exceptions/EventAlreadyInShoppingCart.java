package com.example.seminarksa_wp.model.exceptions;

public class EventAlreadyInShoppingCart extends RuntimeException{
        public EventAlreadyInShoppingCart(Long id,Long userId) {
            super(String.format("Event with id %d already exists in shopping cart for user with id %d!",id,userId));
        }
}
