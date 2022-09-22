package com.example.seminarksa_wp.model;

import com.example.seminarksa_wp.model.enumerations.EventType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    private Integer capacity;
    @Enumerated(EnumType.STRING)
    private EventType venueType;

    public Venue() {
    }

    public Venue(String name, Integer capacity,String imageUrl, EventType venueType) {
        this.name = name;
        this.capacity = capacity;
        this.imageUrl = imageUrl;
        this.venueType = venueType;
    }
}
