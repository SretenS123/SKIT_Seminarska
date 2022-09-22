package com.example.seminarksa_wp.web;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import com.example.seminarksa_wp.service.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/venues")
public class VenuesController {

    private final VenueService venueService;

    public VenuesController(VenueService venueService) {
        this.venueService = venueService;
    }


    @GetMapping
    public String getAllEventsPage(@RequestParam(required = false) String error ,
                                   @RequestParam(required = false) String type,
                                   Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Venue> venues = null;
        if(type!= null && !type.isEmpty())
            venues = this.venueService.findAllByVenueType(type);
        else
            venues = this.venueService.findAll();

        model.addAttribute("venues",venues);
        model.addAttribute("bodyContent","venues");
        return "master-template";

    }

    @GetMapping("/add")
    public String createVenuePage(Model model)
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
        model.addAttribute("bodyContent","addVenue");
        model.addAttribute("types",types);
        model.addAttribute("venues",this.venueService.findAll());
        return "master-template";

    }
    @PostMapping()
    public String createPOST(@RequestParam String name,
                             @RequestParam String imageUrl,
                             @RequestParam EventType venueType,
                             @RequestParam Integer capacity)
    {
        Venue venue = this.venueService.create(name,capacity,imageUrl,venueType);
        return "redirect:/venues";

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
        if(this.venueService.findById(id).isPresent())
        {
            Venue venue = this.venueService.findById(id).get();
            model.addAttribute("venue",venue);
            model.addAttribute("bodyContent","addVenue");
            model.addAttribute("types",types);
            model.addAttribute("venues",this.venueService.findAll());
            return "master-template";
        }
        return "redirect:/venue?error=EventNotFound";

    }
    @PostMapping("/{id}")
    public String updatePOST( @PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String imageUrl,
                              @RequestParam EventType venueType,
                              @RequestParam Integer capacity) {
        Venue venue = this.venueService.edit(id,name, capacity,imageUrl,venueType);
        return "redirect:/venues";

    }

    @RequestMapping("/delete/{Id}")
    public String deleteEvent(@PathVariable Long Id)
    {
        this.venueService.deleteById(Id);
        return "redirect:/venues";
    }
}
