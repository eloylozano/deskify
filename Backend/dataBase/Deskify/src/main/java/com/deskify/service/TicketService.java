package com.deskify.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.dto.converter.TicketConverter;
import com.deskify.error.TicketNotFoundException;
import com.deskify.error.UserNotFoundException;
import com.deskify.model.Ticket;
import com.deskify.model.User;
import com.deskify.repository.TicketRepository;
import com.deskify.repository.UserRepository;
import com.deskify.service.interfaces.ITicketService;

import jakarta.transaction.Transactional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    TicketRepository ticketsRepo;
    @Autowired
    UserRepository userRepo;

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public List<TicketResponseDTO> getTicketList() {
        List<Ticket> ticketList = ticketsRepo.findAll();
        return ticketList.stream()
                .map(ticketConverter::convertToTicketResponseDTO) // Uses converter to get ticketResponseDTO
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponseDTO getTicketById(Long id) {

        // Find the ticket by ID
        Ticket ticket = ticketsRepo.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        return ticketConverter.convertToTicketResponseDTO(ticket);
    }

    @Override
    public TicketResponseDTO saveTicket(CreateTicketDTO createTicketDTO) {
        // Creates new ticket
        Ticket ticket = new Ticket();

        // Set title and description
        ticket.setTitle(createTicketDTO.getTitle());
        ticket.setDescription(createTicketDTO.getDescription());

        // Find the user by email address
        User createdBy = userRepo.findByEmail(createTicketDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(createTicketDTO.getEmail()));

        ticket.setCreatedBy(createdBy);
        // Save the ticket
        Ticket savedTicket = ticketsRepo.save(ticket);

        // Convert the saved ticket in to DTO
        return ticketConverter.convertToTicketResponseDTO(savedTicket);
    }

    @Transactional
    @Override
    public void deleteTicket(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTicket'");
    }

    @Override
    public TicketResponseDTO updateTicket(CreateTicketDTO createTicketDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTicket'");
    }

}
