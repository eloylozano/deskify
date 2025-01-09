package com.deskify.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.deskify.dto.*;
import com.deskify.dto.converter.TicketConverter;
import com.deskify.error.*;
import com.deskify.model.*;
import com.deskify.repository.*;
import com.deskify.service.interfaces.ITicketService;
import com.deskify.specs.TicketSpecifications;

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
                                .map(ticketConverter::convertToTicketResponseDTO) // Uses converter to get
                                                                                  // ticketResponseDTO
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
                // Find the ticket by ID
                Ticket ticket = ticketRepo.findById(id)
                                .orElseThrow(() -> new TicketNotFoundException(id));

                // Delete related assignments
                assignmentRepo.deleteByTicketId(id);
                // Eliminate dependent records in ticket_status_history
                ticketHistoryRepo.deleteByTicketId(id);

                // Delete the ticket
                ticketRepo.delete(ticket);
        }

        @Override
        public void assignAgentToTicket(Long ticketId, Long agentId) {
                // Get the ticket from database
                Ticket ticket = ticketRepo.findById(ticketId)
                                .orElseThrow(() -> new TicketNotFoundException(ticketId));

                // Get list of users who have agent roles
                List<User> agents = userRepo.findByRoleId((long) 4);

                // Verify that the agents is available
                User agent = agents.stream()
                                .filter(a -> a.getId().equals(agentId))
                                .findFirst()
                                .orElseThrow(() -> new AgentNotFoundException(agentId));

                // Create a new assignment
                Assignment assignment = new Assignment();
                assignment.setTicket(ticket);
                assignment.setAgent(agent);
                assignment.setAssignedAt(LocalDateTime.now());

                // Save the assignment
                assignmentRepo.save(assignment);
        }

        @Override
        public void changeCategoryToTicket(Long ticketId, Long categoryId) {
                Ticket ticket = ticketRepo.findById(ticketId)
                                .orElseThrow(() -> new TicketNotFoundException(ticketId));
                Category category = categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

                // Set the new category
                ticket.setCategory(category);
                
                // Save the updated ticket
                ticketRepo.save(ticket);
        }

        @Override
        public Page<TicketResponseDTO> getTicketsByAgent(String agentName, Pageable pageable) {

                Specification<Ticket> spec = Specification.where(TicketSpecifications.hasAgent(agentName));

                // Get all the tickets from the repository using the specifications and
                // paginations
                Page<Ticket> ticketsPage = ticketRepo.findAll(spec, pageable);

                // Map tickets to convert into DTOs
                return ticketsPage.map(ticketConverter::convertToTicketResponseDTO);
        }

}
