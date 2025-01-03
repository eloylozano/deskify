package com.deskify.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketHistoryDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.model.Ticket;
import com.deskify.model.TicketHistory;
import com.deskify.repository.TicketHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketConverter {

    private final ModelMapper mapper;
    private final TicketHistoryRepository thRepository; 

    public CreateTicketDTO convertToDTO(Ticket ticket) {
        return mapper.map(ticket, CreateTicketDTO.class);
    }

    public TicketResponseDTO convertToTicketResponseDTO(Ticket ticket) {
        // Convert ticket to ticketdto
        TicketResponseDTO ticketDTO = mapper.map(ticket, TicketResponseDTO.class);

        // Get last status 
        TicketHistory latestHistory = thRepository.findLatestStatusByTicketId(ticket.getId());
        if (latestHistory != null) {
            // Create DTO for the last status and set it to the ticketDTO
            TicketHistoryDTO statusDTO = new TicketHistoryDTO();
            statusDTO.setStatusName(latestHistory.getStatus().getName());
            statusDTO.setChangedAt(latestHistory.getChangedAt());
            ticketDTO.setCurrentStatus(statusDTO);
        }

        return ticketDTO;
    }
}

