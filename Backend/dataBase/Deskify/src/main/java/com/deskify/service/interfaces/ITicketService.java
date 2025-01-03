package com.deskify.service.interfaces;

import java.util.List;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.model.Ticket;

public interface ITicketService {

    public List<TicketResponseDTO> getTicketList();

    public TicketResponseDTO getTicketById(Long id);

    public CreateTicketDTO createTicket(Ticket ticket);

    public CreateTicketDTO updateTicket(Ticket ticket);

    public void deleteTicket(Long id);

}
