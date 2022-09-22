package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import com.example.seminarksa_wp.model.exceptions.VenueIdNotExistException;
import com.example.seminarksa_wp.repository.VenueRepository;
import com.example.seminarksa_wp.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueServiceImpl implements VenueService {


    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> findAll() {
        return this.venueRepository.findAll();
    }

    @Override
    public List<Venue> findAllBySearchKey(String searchKey) {
        return this.venueRepository.findAllByNameLike('%'+searchKey+'%');
    }

    @Override
    public List<Venue> findAllByVenueType(String type) {
        EventType type1 = EventType.valueOf(type.toUpperCase());
        return this.venueRepository.findAllByVenueType(type1);
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return this.venueRepository.findById(id);
    }

    @Override
    public Venue create(String name, Integer capacity,String imageUrl, EventType venueType) {
        Venue venue = new Venue(name,capacity,imageUrl,venueType);
        return this.venueRepository.save(venue);
    }

    @Override
    public Venue edit(Long id, String name, Integer capacity,String imageUrl, EventType venueType) {
        Venue venue = this.venueRepository.findById(id).orElseThrow(()->new VenueIdNotExistException(id));
        venue.setName(name);
        venue.setCapacity(capacity);
        venue.setVenueType(venueType);
        venue.setImageUrl(imageUrl);
        return this.venueRepository.save(venue);
    }

    @Override
    public void deleteById(Long id) {
        this.venueRepository.deleteById(id);
    }
}
