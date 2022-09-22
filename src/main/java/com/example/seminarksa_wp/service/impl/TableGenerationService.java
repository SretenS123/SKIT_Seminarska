package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.Ticket;
import com.example.seminarksa_wp.repository.TicketRepository;
import com.example.seminarksa_wp.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TableGenerationService {
    @Autowired
    private TicketService ticketService;

    public String generateReportMessage(List<Ticket> tickets) {
        StringBuilder stringBuilder = generateCommonHtmlHead(tickets);
        Integer price  = 0;
        for (Ticket ticket : tickets) {
            price = price + ticket.getPrice();
            stringBuilder.append("<tr>");
            stringBuilder.append("<td>").append(ticket.getId()).append("</td>");
            stringBuilder.append("<td>").append(ticket.getEvent().getTitle()).append("</td>");
            stringBuilder.append("<td>").append(ticket.getRandomCode()).append("</td>");
            stringBuilder.append("<td>").append(ticket.getPrice()).append("</td>");
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");
        stringBuilder.append("<h2>TOTAL PRICE PAID: ").append(price).append("</h2>");
        generateCommonFooter(stringBuilder);
        return stringBuilder.toString();
    }


    private StringBuilder generateCommonHtmlHead(List<Ticket> tickets) {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.append("<head>")
                .append("<h1>INFORMATION ABOUT YOUR BOUGHT TICKETS<h1>")
                .append("</head>")
                .append("<body>")
                .append("<h3>You have bought ").append(tickets.size()).append(" tickets!</h3>")
                .append("<table border=1>")
                .append("<tr>")
                .append("<th>Ticket id</th><th>Event Name</th><th>Ticket Code</th><th>Ticket Price</th>")
                .append("</tr>");
    }

    private void generateCommonFooter(StringBuilder stringBuilder) {
        stringBuilder.append("</body>");
    }


    public String generateReportMessage(Long userId) {
        List<Ticket> all = ticketService.findAllByUser(userId);
        return generateReportMessage(all);
    }
}
