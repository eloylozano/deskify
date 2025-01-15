package com.deskify.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.dto.UpdateTicketDTO;
import com.deskify.error.AgentNotFoundException;
import com.deskify.error.CategoryNotFoundException;
import com.deskify.error.PriorityNotFoundException;
import com.deskify.error.StatusNotFoundException;
import com.deskify.error.TicketNotFoundException;
import com.deskify.service.TicketService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/create")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody CreateTicketDTO ticketDTO) {
        TicketResponseDTO ticketResponseDTO = ticketService.saveTicket(ticketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<TicketResponseDTO> updateTicket(@RequestBody UpdateTicketDTO updateTicketDTO) {
        try {
            TicketResponseDTO updatedTicket = ticketService.updateTicket(updateTicketDTO);
            return ResponseEntity.ok(updatedTicket);
        } catch (TicketNotFoundException | StatusNotFoundException | PriorityNotFoundException
                | CategoryNotFoundException | AgentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // or some custom message
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ticketId}/assign")
    public ResponseEntity<String> assignAgentToTicket(@PathVariable Long ticketId, @RequestParam Long agentId) {
        try {
            ticketService.assignAgentToTicket(ticketId, agentId);
            return ResponseEntity.ok("Agent assigned successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{ticketId}/change-category")
    public ResponseEntity<String> changeCategoryToTicket(@PathVariable Long ticketId, @RequestParam Long categoryId) {
        try {
            ticketService.changeCategoryToTicket(ticketId, categoryId);
            return ResponseEntity.ok("Category changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByAgent(
            @RequestParam(required = false) Long agentId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Long statusId) {

        List<TicketResponseDTO> ticketDTOs = ticketService.getTicketsByFilter(agentId, categoryId, priority, date,
        statusId);

        return ResponseEntity.ok(ticketDTOs);
    }

    // TODO: implement a word search engine for tickets 

}
