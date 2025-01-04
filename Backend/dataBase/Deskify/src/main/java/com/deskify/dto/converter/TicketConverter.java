package com.deskify.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.deskify.dto.AgentAssignedDTO;
import com.deskify.dto.ClientDTO;
import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketHistoryDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.model.Assignment;
import com.deskify.model.Ticket;
import com.deskify.model.TicketHistory;
import com.deskify.model.User;
import com.deskify.repository.AssignmentRepository;
import com.deskify.repository.TicketHistoryRepository;
import com.deskify.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketConverter {

    private final ModelMapper mapper;
    private final TicketHistoryRepository thRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

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

        // Set client (createdBy)
        if (ticket.getCreatedBy() != null) {
            ClientDTO clientDTO = new ClientDTO(
                ticket.getCreatedBy().getFirstName() + " " + ticket.getCreatedBy().getLastName(),
                ticket.getCreatedBy().getEmail()
            );
            ticketDTO.setClient(clientDTO);
        }

        // Set agent (assignedTo)
        Assignment latestAssignment = assignmentRepository.findTopByTicketIdOrderByAssignedAtDesc(ticket.getId());
        if (latestAssignment != null && latestAssignment.getAgent() != null) {
            AgentAssignedDTO agentDTO = new AgentAssignedDTO(
                latestAssignment.getAgent().getFirstName() + " " + latestAssignment.getAgent().getLastName(),
                latestAssignment.getAgent().getEmail()
            );
            ticketDTO.setAgent(agentDTO);
        }

        return ticketDTO;
    }
}