package com.example.seminarksa_wp.web;

import com.example.seminarksa_wp.model.Ticket;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.service.TicketService;
import com.example.seminarksa_wp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/myTickets")
public class TicketController {
    private final UserService userService;
    private final TicketService ticketService;

    public TicketController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public String getTickets(Authentication authentication,
            Model model)
    {
        User user =(User) authentication.getPrincipal();
        List<Ticket> tickets = this.ticketService.findAllByUser(user.getId());
        Integer price = 0;
        for(Ticket ticket : tickets)
        {
            price = price + ticket.getPrice();
        }
        model.addAttribute("tickets",tickets);
        model.addAttribute("price",price);
        model.addAttribute("bodyContent","myTickets");
        return "master-template";
    }

}
