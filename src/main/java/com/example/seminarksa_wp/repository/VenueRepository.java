package com.example.seminarksa_wp.repository;

import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Long> {
    List<Venue> findAllByVenueType(EventType type);
    List<Venue> findAllByNameLike(String searchKey);
}
