package com.example.seminarksa_wp.service;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Event> listAllEventsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(Long userId);
    ShoppingCart addEventsToShoppingCart(Long userId,Long eventId);
    ShoppingCart deleteEventToShoppingCart(Long userId,Long eventId);
    ShoppingCart buyTicket(Long userId,Long eventId,Integer quantity);
}
