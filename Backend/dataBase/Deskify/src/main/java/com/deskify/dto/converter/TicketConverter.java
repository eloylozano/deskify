package com.deskify.dto.converter;

import java.util.List;

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
import com.deskify.repository.AssignmentRepository;
import com.deskify.repository.TicketHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketConverter {

    private final ModelMapper mapper;
    private final TicketHistoryRepository thRepository;
    private final AssignmentRepository assignmentRepository;

    public CreateTicketDTO convertToDTO(Ticket ticket) {
        return mapper.map(ticket, CreateTicketDTO.class);
    }

    public TicketResponseDTO convertToTicketResponseDTO(Ticket ticket) {
        // Convert the ticket to a TicketResponseDTO
        TicketResponseDTO ticketDTO = mapper.map(ticket, TicketResponseDTO.class);

        // Get the list of status ordered and pic the latest 
        List<TicketHistory> histories = thRepository.findByTicketIdOrderByChangedAtDesc(ticket.getId());
        if (!histories.isEmpty()) {
            TicketHistory latestHistory = histories.get(0); // El más reciente gracias al orden DESC
            TicketHistoryDTO statusDTO = new TicketHistoryDTO();
            statusDTO.setStatusName(latestHistory.getStatus().getName());
            statusDTO.setChangedAt(latestHistory.getChangedAt());
            ticketDTO.setCurrentStatus(statusDTO);
        }

        // Set the client who created the ticket
        if (ticket.getCreatedBy() != null) {
            ClientDTO clientDTO = new ClientDTO(
                    ticket.getCreatedBy().getFirstName() + " " + ticket.getCreatedBy().getLastName(),
                    ticket.getCreatedBy().getEmail());
            ticketDTO.setClient(clientDTO);
        }

        // Get the agent who is associated with the ticket
        List<Assignment> assignments = assignmentRepository.findByTicketIdOrderByAssignedAtDesc(ticket.getId());
        if (!assignments.isEmpty()) {
            Assignment latestAssignment = assignments.get(0); // Pick the first
            if (latestAssignment.getAgent() != null) {
                AgentAssignedDTO agentDTO = new AgentAssignedDTO(
                        latestAssignment.getAgent().getFirstName() + " " + latestAssignment.getAgent().getLastName(),
                        latestAssignment.getAgent().getEmail());
                ticketDTO.setAgent(agentDTO);
            }
        }

        return ticketDTO;
    }

}