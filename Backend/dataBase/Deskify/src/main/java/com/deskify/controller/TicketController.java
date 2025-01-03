package com.deskify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deskify.dto.TicketResponseDTO;
import com.deskify.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/all")
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        List<TicketResponseDTO> ticketList = ticketService.getTicketList();
        if (ticketList.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return empty response
        }
        return ResponseEntity.ok(ticketList); // Return OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable("id") Long id) {
        TicketResponseDTO ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build(); // Return not found
        }
        return ResponseEntity.ok(ticket); // Return OK
    }

}
