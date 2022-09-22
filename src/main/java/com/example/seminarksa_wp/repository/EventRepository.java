package com.example.seminarksa_wp.repository;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.enumerations.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    void deleteByTitle(String title);
    List<Event> findAllByEventType(EventType eventType);
    List<Event> findAllByEventTypeAndTitleLike(EventType eventType,String searchKey);
    List <Event> findByTitleContainingOrderByDate(@Param("searchKey") String searchKey);


}
