package com.deskify.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.dto.UpdateTicketDTO;

public interface ITicketService {

    public List<TicketResponseDTO> getTicketList();

    public TicketResponseDTO getTicketById(Long id);

    public TicketResponseDTO saveTicket(CreateTicketDTO createTicketDTO);

    public TicketResponseDTO updateTicket(UpdateTicketDTO updateTicketDTO);

    public void deleteTicket(Long id);

    public void assignAgentToTicket(Long ticketId, Long agentId);

    public void changeCategoryToTicket(Long ticketId, Long agentId);

    public Page<TicketResponseDTO> getTicketsByAgent(String agentName, Pageable pageable);

}
