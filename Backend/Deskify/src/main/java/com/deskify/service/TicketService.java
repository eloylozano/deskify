package com.deskify.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

                // Update status if provided
                if (updateTicketDTO.getStatusId() != null) {
                        Status newStatus = statusRepo.findById(updateTicketDTO.getStatusId())
                                        .orElseThrow(() -> new StatusNotFoundException(updateTicketDTO.getStatusId()));

                        // Create a new ticket status history record to log the status change
                        TicketHistory statusHistory = new TicketHistory();
                        statusHistory.setTicket(ticket);
                        statusHistory.setStatus(newStatus);
                        statusHistory.setChangedAt(LocalDateTime.now());
                        ticketHistoryRepo.save(statusHistory);
                }

                // Update priority if provided
                if (updateTicketDTO.getPriorityId() != null) {
                        Priority newPriority = priorityRepo.findById(updateTicketDTO.getPriorityId())
                                        .orElseThrow(() -> new PriorityNotFoundException(
                                                        updateTicketDTO.getPriorityId()));
                        ticket.setPriority(newPriority);
                }

                // Update category if provided
                if (updateTicketDTO.getCategoryId() != null) {
                        Category newCategory = categoryRepo.findById(updateTicketDTO.getCategoryId())
                                        .orElseThrow(() -> new CategoryNotFoundException(
                                                        updateTicketDTO.getCategoryId()));
                        ticket.setCategory(newCategory);
                }

                // Update agent assignment if provided
                if (updateTicketDTO.getUserId() != null) {
                        User newAgent = userRepo.findById(updateTicketDTO.getUserId())
                                        .orElseThrow(() -> new AgentNotFoundException(updateTicketDTO.getUserId()));

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
                }

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
        public List<TicketResponseDTO> getTicketsByFilter(Long agentId, Long categoryId, String prioriyName,
                        LocalDate date, Long statusId) {
                // SPecifcation null
                Specification<Ticket> spec = Specification.where(null);

                // Add filter if there's agent
                if (agentId != null) {
                        spec = spec.and(TicketSpecifications.hasAgent(agentId));
                }

                // Add filter if there's category
                if (categoryId != null) {
                        spec = spec.and(TicketSpecifications.hasCategory(categoryId));
                }

                // Add filter if there's priority
                if (prioriyName != null) {
                        spec = spec.and(TicketSpecifications.hasPriority(prioriyName));
                }

                // Add filter if there's date
                if (date != null) {
                        spec = spec.and(TicketSpecifications.createdBefore(date));
                }

                if (statusId != null) {
                        spec = spec.and(TicketSpecifications.hasStatus(statusId));
                }

                // Get all tickets that match the filter
                List<Ticket> tickets = ticketRepo.findAll(spec);

                // Convert ticket to DTO
                return tickets.stream()
                                .map(ticketConverter::convertToTicketResponseDTO)
                                .collect(Collectors.toList());
        }

        private String prepareQuery(String query) {
                // Refactor query and divide into segments
                String[] words = query.split("\\s+");

                // Create regular expression
                return ".*" + String.join(".*", words) + ".*";
        }

        @Override
        public List<TicketResponseDTO> searchTickets(String query) {

                String regexQuery = prepareQuery(query);

                List<Ticket> tickets = ticketRepo.searchByQuery(regexQuery);

                return tickets.stream()
                                .map(ticketConverter::convertToTicketResponseDTO)
                                .collect(Collectors.toList());
        }

        @Override
         public TicketStatusSummaryDTO getTicketStatusSummary() {
        List<Object[]> results = ticketRepo.countTicketsByCurrentStatus();

        Map<String, Long> statusCounts = new HashMap<>();
        long totalTickets = 0;

        for (Object[] result : results) {
            String statusName = (String) result[0];
            Long count = (Long) result[1];

            statusCounts.put(statusName, count);
            totalTickets += count;
        }

        return new TicketStatusSummaryDTO(totalTickets, statusCounts);
    }

}
