package com.deskify.service.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.deskify.dto.CreateAdminTicketDTO;
import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.dto.TicketStatusSummaryDTO;
import com.deskify.dto.UpdateTicketDTO;
import com.deskify.model.Ticket;
import com.deskify.model.User;

public interface ITicketService {

    public List<TicketResponseDTO> getTicketList();

    public TicketResponseDTO getTicketById(Long id);

    public Ticket createTicket(CreateAdminTicketDTO createAdminTicketDTO);

    public TicketResponseDTO saveTicket(CreateTicketDTO createTicketDTO);

    public TicketResponseDTO updateTicket(UpdateTicketDTO updateTicketDTO);

    public void deleteTicket(Long id);

    public void assignAgentToTicket(Long ticketId, Long agentId);

    public void changeCategoryToTicket(Long ticketId, Long agentId);

    public List<TicketResponseDTO> getTicketsByFilter(Long agentId, Long categoryId, String priorityName,
            LocalDate date, Long statusId);

    public List<TicketResponseDTO> searchTickets(String query);

    public TicketStatusSummaryDTO getTicketStatusSummary();
}
