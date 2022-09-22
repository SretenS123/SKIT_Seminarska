package com.example.seminarksa_wp.repository;

import com.example.seminarksa_wp.model.Ticket;
import com.example.seminarksa_wp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByUser(User user);
}
