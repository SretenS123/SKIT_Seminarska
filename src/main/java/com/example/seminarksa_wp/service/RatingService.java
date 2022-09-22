package com.example.seminarksa_wp.service;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Rating;
import com.example.seminarksa_wp.model.User;

import java.util.List;

public interface RatingService {
    List<Rating> listAllByEvent(Long eventId);

    List<Rating> listAllByUser(Long userId);

    Rating create(String comment, Long userId, Long eventId);
}
