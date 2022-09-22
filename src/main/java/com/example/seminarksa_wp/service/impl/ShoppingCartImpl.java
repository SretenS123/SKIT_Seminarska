package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.ShoppingCart;
import com.example.seminarksa_wp.model.Ticket;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.enumerations.ShoppingCartStatus;
import com.example.seminarksa_wp.model.exceptions.*;
import com.example.seminarksa_wp.repository.EventRepository;
import com.example.seminarksa_wp.repository.ShoppingCartRepository;
import com.example.seminarksa_wp.repository.TicketRepository;
import com.example.seminarksa_wp.repository.UserRepository;
import com.example.seminarksa_wp.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public ShoppingCartImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, EventRepository eventRepository, TicketRepository ticketRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Event> listAllEventsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFound(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getEvents();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(()-> new UserIdNotFoundException(userId));
        return this.shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(()-> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addEventsToShoppingCart(Long userId, Long eventId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);//Zemame aktivna shopping cart za user
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new EventIdNotExistException(eventId));
        if(shoppingCart.getEvents().stream().filter(r->r.getId().equals(eventId)).collect(Collectors.toList()).size()>0)
            throw new EventAlreadyInShoppingCart(eventId,userId);
        shoppingCart.getEvents().add(event);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleteEventToShoppingCart(Long userId, Long eventId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new EventIdNotExistException(eventId));
        shoppingCart.getEvents().remove(event);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart buyTicket(Long userId, Long eventId,Integer quantity) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new UserIdNotFoundException(userId));
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new EventIdNotExistException(eventId));
        if(event.getTicketsLeft()<quantity)
            throw new InvalidTicketsException();
        shoppingCart.getEvents().remove(event);
        Integer price = event.getPrice();
        for(var i=0;i<quantity;i++)
        {
            Ticket ticket = new Ticket(price,event,user);
            this.ticketRepository.save(ticket);
        }

        Integer ticketsLeft = event.getTicketsLeft() - quantity;
        if(ticketsLeft<1)
            event.setSoldOut(true);
        event.setTicketsLeft(ticketsLeft);
        this.eventRepository.save(event);
        return shoppingCart;


    }
}
