package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.Dto.EventDto;
import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import com.example.seminarksa_wp.model.exceptions.EventIdNotExistException;
import com.example.seminarksa_wp.model.exceptions.VenueIdNotExistException;
import com.example.seminarksa_wp.repository.EventRepository;
import com.example.seminarksa_wp.repository.UserRepository;
import com.example.seminarksa_wp.repository.VenueRepository;
import com.example.seminarksa_wp.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class EventServiceImpl implements EventService {

    private final  EventRepository eventRepository;
    private final UserRepository userRepository;
    private final VenueRepository venueRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Event> listAll() {
        log.info("Listing All Events!");
        return this.eventRepository.findAll();

    }

    @Override
    public List<Event> findAllByEventType(String type) {
        EventType type1 = EventType.valueOf(type.toUpperCase());
        return this.eventRepository.findAllByEventType(type1);
    }

    @Override
    public List<Event> findAllByEventTypeAndSearchKey(String type, String searchKey) {
        EventType type1 = EventType.valueOf(type.toUpperCase());
        return this.eventRepository.findAllByEventTypeAndTitleLike(type1,'%' + searchKey + '%');

    }

    @Override
    public List<Event> findAllBySearchKey(String searchKey) {
        log.info("Listing all Events By searchKey: %s",searchKey);
        return this.eventRepository.findByTitleContainingOrderByDate(searchKey);

    }

    @Override
    public Event create(String title, String description, String imageUrl, EventType eventType, LocalDate date, Long venueId,Integer price,Integer ticketsLeft) {
        Venue venue  = this.venueRepository.findById(venueId).orElseThrow(()->new VenueIdNotExistException(venueId));
        Event event = new Event(title,description,imageUrl,eventType,date,venue,price,ticketsLeft);
        log.info("Creatng Event with title %s",title);
        return this.eventRepository.save(event);
    }




//    @Override
//    public Optional<Event> create(EventDto eventDto) {
//        Long venueId =eventDto.getLocation().getId();
//        Venue venue = this.venueRepository.findById(venueId).orElseThrow(()->new VenueIdNotExistException(venueId));
//        Event event = new Event(eventDto.getTitle(),eventDto.getDescription(),eventDto.getEventType(),eventDto.getLocation());
//        this.eventRepository.save(event);
//        return Optional.of(event);
    //}
    @Override
    public Event edit(Long id, String title, String description,String imageUrl, EventType eventType,LocalDate date, Long venueId,Integer price,Integer ticketsLeft) {
        Venue venue  = this.venueRepository.findById(venueId).orElseThrow(()->new VenueIdNotExistException(venueId));
        Event event = this.eventRepository.findById(id).orElseThrow(()->new EventIdNotExistException(id));
        event.setTitle(title);
        event.setEventType(eventType);
        event.setDescription(description);
        event.setLocation(venue);
        event.setImageUrl(imageUrl);
        event.setDate(date);
        event.setPrice(price);
        event.setTicketsLeft(ticketsLeft);
        log.info("We are editing Event with id %d",id);
        return this.eventRepository.save(event);
    }
//    @Override
//    public Optional<Event> edit(Long id, EventDto eventDto) {
//        Event event = this.eventRepository.findById(id).orElseThrow(()->new EventIdNotExistException(id));
//
//        event.setTitle(eventDto.getTitle());
//        event.setEventType(eventDto.getEventType());
//        event.setDescription(eventDto.getDescription());
//
//        Long venueId =eventDto.getLocation().getId();
//        Venue venue = this.venueRepository.findById(venueId).orElseThrow(()->new VenueIdNotExistException(venueId));
//        event.setLocation(venue);
//        this.eventRepository.save(event);
//        return Optional.of(event);
//    }


    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }
}
