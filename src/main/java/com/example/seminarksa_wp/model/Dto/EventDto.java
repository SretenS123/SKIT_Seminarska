package com.example.seminarksa_wp.model.Dto;

import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import lombok.Data;

@Data
public class EventDto {
    private String title;

    private String description;

    private EventType eventType;

    private Venue location;
}
