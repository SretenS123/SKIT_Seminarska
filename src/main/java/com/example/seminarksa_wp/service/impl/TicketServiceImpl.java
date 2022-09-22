package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Ticket;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.exceptions.EventIdNotExistException;
import com.example.seminarksa_wp.model.exceptions.TicketIdNotFoundException;
import com.example.seminarksa_wp.model.exceptions.UserIdNotFoundException;
import com.example.seminarksa_wp.repository.EventRepository;
import com.example.seminarksa_wp.repository.TicketRepository;
import com.example.seminarksa_wp.repository.UserRepository;
import com.example.seminarksa_wp.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return  this.ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllByUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new UserIdNotFoundException(userId));
        return this.ticketRepository.findAllByUser(user);

    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return this.ticketRepository.findById(id);
    }

    @Override
    public Ticket create(Integer price, Long eventId, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new UserIdNotFoundException(userId));
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new EventIdNotExistException(eventId));
        Ticket ticket = new Ticket(price,event,user);
        return this.ticketRepository.save(ticket);

    }

    @Override
    public Ticket edit(Long id, Integer price, Long eventId, Long userId) {
        Ticket ticket = this.ticketRepository.findById(id).orElseThrow(()->new TicketIdNotFoundException(id));
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new EventIdNotExistException(eventId));
        User user = this.userRepository.findById(userId).orElseThrow(()->new UserIdNotFoundException(userId));
        ticket.setPrice(price);
        ticket.setEvent(event);
        ticket.setUser(user);

        return this.ticketRepository.save(ticket);
    }

    @Override
    public void deleteById(Long id) {
        this.ticketRepository.deleteById(id);
    }
}
