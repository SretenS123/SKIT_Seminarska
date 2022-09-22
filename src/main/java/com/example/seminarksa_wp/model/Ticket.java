package com.example.seminarksa_wp.model;

import lombok.Data;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.util.Random;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    private String randomCode;

    @ManyToOne
    private Event event;
    @ManyToOne
    private User user;

    public Ticket() {
    }

    public Ticket(Integer price, Event event, User user) {
        this.price = price;
        this.event = event;
        this.user = user;
        this.randomCode = RandomString.make(20);
    }
}
