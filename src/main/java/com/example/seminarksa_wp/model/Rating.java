package com.example.seminarksa_wp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private LocalDateTime date;

    private Long ago;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;

    public Rating() {
    }

    public Rating(String comment, User user, Event event) {
        this.comment = comment;
        this.user = user;
        this.event = event;
        this.date = LocalDateTime.now();

    }
}
