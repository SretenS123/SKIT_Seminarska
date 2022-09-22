package com.example.seminarksa_wp.service;

import com.example.seminarksa_wp.model.Dto.EventDto;
import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> listAll();

    Optional<Event> findById(Long id);

   // Optional<Event> create(EventDto eventDto);
    List<Event> findAllByEventType(String type);

    List<Event> findAllByEventTypeAndSearchKey(String type,String searchKey);

    List<Event> findAllBySearchKey(String searchKey);

    Event create(String title, String description, String imageUrl, EventType eventType, LocalDate date, Long venueId,Integer Price,Integer ticketsLeft);

   // Optional<Event> edit (Long id, EventDto eventDto);

    Event edit (Long id, String title, String description,String imageUrl, EventType eventType,LocalDate date, Long venueId,Integer price,Integer ticketsLeft);


    void deleteById(Long id);
}
