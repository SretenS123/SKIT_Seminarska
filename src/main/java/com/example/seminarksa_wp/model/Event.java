package com.example.seminarksa_wp.model;

import com.example.seminarksa_wp.model.enumerations.EventType;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDate date;
    @OneToOne
    private Venue location;

    private Integer price;

    private boolean soldOut;

    private Integer ticketsLeft;
    @ManyToMany
    private List<User> users;
    @OneToMany(mappedBy = "event",fetch = FetchType.EAGER)
    private List<Rating> ratings;

    public Event() {
    }

    public Event(String title, String description,String imageUrl, EventType eventType,LocalDate date, Venue location,Integer price,Integer ticketsLeft) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.eventType = eventType;
        this.date = date;
        this.location = location;
        this.price = price;
        this.ticketsLeft = ticketsLeft;
        this.users = new ArrayList<>();
        this.soldOut = false;
    }
}
