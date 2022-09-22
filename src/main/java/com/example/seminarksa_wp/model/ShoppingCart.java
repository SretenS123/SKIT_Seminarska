package com.example.seminarksa_wp.model;


import com.example.seminarksa_wp.model.enumerations.ShoppingCartStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Event> events;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {

    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.events = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
