package com.deskify.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.model.Ticket;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketConverter {

    private final ModelMapper mapper;

    public CreateTicketDTO convertToDTO(Ticket ticket) {
        return mapper.map(ticket, CreateTicketDTO.class);
    }

    public TicketResponseDTO convertToTicketResponseDTO(Ticket ticket) {
        return mapper.map(ticket, TicketResponseDTO.class); 
    }

}
