package com.deskify.controller;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.model.Ticket;
import com.deskify.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tickets")
public class AdminTicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getTicketList());
        return "admin/tickets";
    }

    @GetMapping("/{id}")
    public String getTicket(@PathVariable Long id, Model model) {
        TicketResponseDTO ticketDTO = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticketDTO);
        return "admin/edit-ticket";
    }

    @PostMapping("/edit")
    public String editTicket(@ModelAttribute CreateTicketDTO ticket) {
        ticketService.saveTicket(ticket);
        return "redirect:/admin/tickets";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/admin/tickets";
    }
}
