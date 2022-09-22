package com.example.seminarksa_wp.service;

import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;

import java.util.List;
import java.util.Optional;

public interface VenueService {

    List<Venue> findAll();

    List<Venue> findAllByVenueType(String type);

    List<Venue> findAllBySearchKey(String searchKey);

    Optional<Venue> findById(Long id);


    Venue create(String name, Integer capacity,String imageUrl, EventType venueType);

    Venue edit(Long id,String name, Integer capacity,String imageUrl, EventType venueType);

    void deleteById(Long id);
}
