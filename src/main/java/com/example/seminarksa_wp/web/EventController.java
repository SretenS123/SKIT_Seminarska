package com.example.seminarksa_wp.web;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Rating;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.enumerations.EventType;
import com.example.seminarksa_wp.model.exceptions.EventIdNotExistException;
import com.example.seminarksa_wp.service.EventService;
import com.example.seminarksa_wp.service.RatingService;
import com.example.seminarksa_wp.service.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final VenueService venueService;
    private final RatingService ratingService;

    public EventController(EventService eventService, VenueService venueService, RatingService ratingService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.ratingService = ratingService;
    }

    @GetMapping("")
    public String getAllEventsPage(@RequestParam(required = false) String error ,
                                   @RequestParam(required = false) String type,
                                   @RequestParam(required = false) String searchKey,
                                   Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Event> eventList = new ArrayList<>();
         if (type!= null && !type.isEmpty() && searchKey==null)
            eventList = this.eventService.findAllByEventType(type);
        else if(searchKey!=null && !searchKey.isEmpty() && type== null )
            eventList = this.eventService.findAllBySearchKey(searchKey.toString());
        else
            eventList = this.eventService.listAll();

            model.addAttribute("eventList",eventList);
            model.addAttribute("bodyContent","events");
            return "master-template";

    }

    @GetMapping("/add")
    public String createEventPage(Model model)
    {
        List<EventType> types = new ArrayList<>();
        types.add(EventType.PARTY);
        types.add(EventType.THEATRE);
        types.add(EventType.BAR);
        types.add(EventType.PARTY);
        types.add(EventType.NIGHTCLUB);
        types.add(EventType.CONCERT);
        types.add(EventType.CINEMA);

        types.add(EventType.LIVE_MUSIC);
        model.addAttribute("bodyContent","addEvent");
        model.addAttribute("types",types);
        model.addAttribute("venues",this.venueService.findAll());
        return "master-template";

    }
    @PostMapping()
    public String createPOST(@RequestParam String title,
                              @RequestParam String description,
                              @RequestParam String imageUrl,
                              @RequestParam EventType eventType,
                              @RequestParam String date,
                              @RequestParam Long venueId,
                             @RequestParam Integer ticketsLeft,
                             @RequestParam Integer price)
    {
        Event event = this.eventService.create(title, description, imageUrl,eventType,LocalDate.parse(date), venueId,price,ticketsLeft);
        return "redirect:/events";

    }
    @GetMapping("/edit/{id}")
    public String editEventPage(@PathVariable Long id, Model model)
    {
        List<EventType> types = new ArrayList<>();
        types.add(EventType.PARTY);
        types.add(EventType.THEATRE);
        types.add(EventType.BAR);
        types.add(EventType.PARTY);
        types.add(EventType.NIGHTCLUB);
        types.add(EventType.CONCERT);
        types.add(EventType.CINEMA);

        types.add(EventType.LIVE_MUSIC);
        if(this.eventService.findById(id).isPresent())
        {
            Event event = this.eventService.findById(id).get();
            model.addAttribute("event",event);
            model.addAttribute("bodyContent","addEvent");
            model.addAttribute("types",types);
            model.addAttribute("venues",this.venueService.findAll());
            return "master-template";
        }
        return "redirect:/events?error=EventNotFound";

    }
    @PostMapping("/{id}")
    public String updatePOST( @PathVariable Long id,
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam String imageUrl,
                              @RequestParam EventType eventType,
                              @RequestParam String date,
                              @RequestParam Long venueId,
                              @RequestParam Integer price,
                              @RequestParam Integer ticketsLeft) {
        Event event = this.eventService.edit(id,title, description, imageUrl, eventType,LocalDate.parse(date), venueId,price,ticketsLeft);
        return "redirect:/events";

    }

    @RequestMapping("/delete/{Id}")
    public String deleteEvent(@PathVariable Long Id)
    {
        this.eventService.deleteById(Id);
        return "redirect:/events";
    }

    @GetMapping("/{id}/ratings")
    public String getRatingFromEvent(@PathVariable Long id,
                                     Model model){
        Event event = this.eventService.findById(id).orElseThrow(()->new EventIdNotExistException(id));
        List<Rating> ratings = this.ratingService.listAllByEvent(id);
        model.addAttribute("eventId",id);
        model.addAttribute("event",event);
        model.addAttribute("ratings",ratings);
        model.addAttribute("bodyContent","ratings");
        return "master-template";
    }
    @PostMapping("/{id}/ratings")
    public String createRatingFromEvent(@RequestParam String tekst,
                                        @RequestParam Long id,
                                        Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Rating rating = this.ratingService.create(tekst, user.getId(), id);
        return "redirect:/events/{id}/ratings";
    }



}
