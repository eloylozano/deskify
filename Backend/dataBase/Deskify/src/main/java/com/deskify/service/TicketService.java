package com.deskify.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.dto.converter.TicketConverter;
import com.deskify.model.Ticket;
import com.deskify.repository.TicketRepository;
import com.deskify.service.interfaces.ITicketService;

import jakarta.transaction.Transactional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    TicketRepository ticketsRepo;

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
        Optional<Ticket> ticketOp = ticketsRepo.findById(id);
        if (ticketOp.isPresent()) {
            return ticketConverter.convertToTicketResponseDTO(ticketOp.get());
    } else {
            return null;
        }
    }

    @Override
    public CreateTicketDTO createTicket(Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createTicket'");
    }

    @Override
    public CreateTicketDTO updateTicket(Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTicket'");
    }

    @Transactional
    @Override
    public void deleteTicket(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTicket'");
    }

}
