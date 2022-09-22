package com.example.seminarksa_wp.service;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Ticket;
import com.example.seminarksa_wp.model.User;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> findAll();

    List<Ticket> findAllByUser(Long userId);

    Optional<Ticket> findById(Long id);


    Ticket create(Integer price, Long eventId, Long userId);

    Ticket edit(Long id,Integer price, Long eventId, Long userId);

    void deleteById(Long id);

}
