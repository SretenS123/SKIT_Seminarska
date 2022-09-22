package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Rating;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.exceptions.EventIdNotExistException;
import com.example.seminarksa_wp.model.exceptions.UserIdNotFoundException;
import com.example.seminarksa_wp.repository.EventRepository;
import com.example.seminarksa_wp.repository.RatingRepository;
import com.example.seminarksa_wp.repository.UserRepository;
import com.example.seminarksa_wp.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Slf4j
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RatingServiceImpl(RatingRepository ratingRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Rating> listAllByEvent(Long eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new EventIdNotExistException(eventId));

        List<Rating> ratings = this.ratingRepository.findAllByEvent(event);
        for(Rating rating: ratings){
            LocalDateTime ratingDate = rating.getDate();
            LocalDateTime realTime = LocalDateTime.now();
            Long minutes = ratingDate.until(realTime,ChronoUnit.MINUTES);
            rating.setAgo(minutes);


        }
        log.info("Listing Rating by Event!");
        return  ratings;
    }

    @Override
    public List<Rating> listAllByUser(Long userId) {
        User user  = this.userRepository.findById(userId).orElseThrow(()->new UserIdNotFoundException(userId));

        List<Rating> ratings =  this.ratingRepository.findAllByUser(user);
        for(Rating rating: ratings){
            LocalDateTime ratingDate = rating.getDate();
            LocalDateTime realTime = LocalDateTime.now();
            Long minutes = ratingDate.until(realTime,ChronoUnit.MINUTES);
            rating.setAgo(minutes);


        }
        log.info("Listing all Rating by User!");
        return ratings;
    }

    @Override
    @Transactional
    public Rating create(String comment, Long userId, Long eventId) {
        User user  = this.userRepository.findById(userId).orElseThrow(()->new UserIdNotFoundException(userId));
        Event event = this.eventRepository.findById(eventId).orElseThrow(()->new EventIdNotExistException(eventId));
        Rating rating = new Rating(comment,user,event);
        log.info("Creating rating!");
        return this.ratingRepository.save(rating);
    }
}
