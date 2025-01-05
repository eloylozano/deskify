package com.deskify.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.dto.UpdateTicketDTO;
import com.deskify.dto.converter.TicketConverter;
import com.deskify.error.AgentNotFoundException;
import com.deskify.error.CategoryNotFoundException;
import com.deskify.error.PriorityNotFoundException;
import com.deskify.error.StatusNotFoundException;
import com.deskify.error.TicketNotFoundException;
import com.deskify.error.UserNotFoundException;
import com.deskify.model.Assignment;
import com.deskify.model.Category;
import com.deskify.model.Priority;
import com.deskify.model.Status;
import com.deskify.model.Ticket;
import com.deskify.model.TicketHistory;
import com.deskify.model.User;
import com.deskify.repository.AssignmentRepository;
import com.deskify.repository.CategoryRepository;
import com.deskify.repository.PriorityRepository;
import com.deskify.repository.StatusRepository;
import com.deskify.repository.TicketHistoryRepository;
import com.deskify.repository.TicketRepository;
import com.deskify.repository.UserRepository;
import com.deskify.service.interfaces.ITicketService;

import jakarta.transaction.Transactional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    TicketRepository ticketRepo;
    @Autowired
    TicketHistoryRepository ticketHistoryRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    StatusRepository statusRepo;
    @Autowired
    PriorityRepository priorityRepo;
    @Autowired
    AssignmentRepository assignmentRepo;

    @Autowired
    private TicketConverter ticketConverter;

    @Override
    public List<TicketResponseDTO> getTicketList() {
        List<Ticket> ticketList = ticketRepo.findAll();
        return ticketList.stream()
                .map(ticketConverter::convertToTicketResponseDTO) // Uses converter to get ticketResponseDTO
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponseDTO getTicketById(Long id) {

        // Find the ticket by ID
        Ticket ticket = ticketRepo.findById(id)
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
        Ticket savedTicket = ticketRepo.save(ticket);

        // Convert the saved ticket in to DTO
        return ticketConverter.convertToTicketResponseDTO(savedTicket);
    }

    @Transactional
    @Override
    public TicketResponseDTO updateTicket(UpdateTicketDTO updateTicketDTO) {
        // Find the ticket by ID
        Ticket ticket = ticketRepo.findById(updateTicketDTO.getTicketId())
                .orElseThrow(() -> new TicketNotFoundException(updateTicketDTO.getTicketId()));

        // Find the new status from statusRepo
        Status newStatus = statusRepo.findByName(updateTicketDTO.getStatusName())
                .stream()
                .findFirst()
                .orElseThrow(() -> new StatusNotFoundException(updateTicketDTO.getStatusName()));

        // Create a new ticket status history record to log the status change
        TicketHistory statusHistory = new TicketHistory();
        statusHistory.setTicket(ticket);
        statusHistory.setStatus(newStatus);
        statusHistory.setChangedAt(LocalDateTime.now());

        // Save the status history
        ticketHistoryRepo.save(statusHistory);

        // Update other fields (priority, category, agent)
        Priority newPriority = priorityRepo.findByName(updateTicketDTO.getPriorityName())
                .orElseThrow(() -> new PriorityNotFoundException(updateTicketDTO.getPriorityName()));
        ticket.setPriority(newPriority);

        Category newCategory = categoryRepo.findById(updateTicketDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(updateTicketDTO.getCategoryId()));
        ticket.setCategory(newCategory);

        User newAgent = userRepo.findByEmail(updateTicketDTO.getAgentEmail())
                .orElseThrow(() -> new AgentNotFoundException(updateTicketDTO.getAgentEmail()));

        // Remove old assignment if exists
        Assignment oldAssignment = assignmentRepo.findByTicket(ticket);
        if (oldAssignment != null) {
            assignmentRepo.delete(oldAssignment);
        }

        // Assign new agent
        Assignment newAssignment = new Assignment();
        newAssignment.setTicket(ticket);
        newAssignment.setAgent(newAgent);
        assignmentRepo.save(newAssignment);

        // Save updated ticket
        Ticket updatedTicket = ticketRepo.save(ticket);

        // Return the updated ticket response
        return ticketConverter.convertToTicketResponseDTO(updatedTicket);
    }

    @Transactional
    @Override
    public void deleteTicket(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTicket'");
    }

}
