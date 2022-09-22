package com.example.seminarksa_wp.repository;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Rating;
import com.example.seminarksa_wp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findAllByUser(User user);
    List<Rating> findAllByEvent(Event event);
}
