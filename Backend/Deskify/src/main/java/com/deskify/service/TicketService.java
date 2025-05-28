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
                                .map(ticketConverter::convertToTicketResponseDTO) 
                                .collect(Collectors.toList());
        }

        @Override
        public TicketResponseDTO getTicketById(Long id) {

                Ticket ticket = ticketRepo.findById(id)
                                .orElseThrow(() -> new TicketNotFoundException(id));

                return ticketConverter.convertToTicketResponseDTO(ticket);
        }

        @Override
        public Ticket createTicket(CreateAdminTicketDTO createAdminTicketDTO) {

                Category category = categoryRepo.findById(createAdminTicketDTO.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                Priority priority = priorityRepo.findById(createAdminTicketDTO.getPriorityId())
                                .orElseThrow(() -> new RuntimeException("Priority not found"));
                Status status = statusRepo.findById(createAdminTicketDTO.getStatusId())
                                .orElseThrow(() -> new RuntimeException("Status not found"));

                Ticket ticket = new Ticket();
                ticket.setTitle(createAdminTicketDTO.getTitle());
                ticket.setDescription(createAdminTicketDTO.getDescription());
                ticket.setCategory(category);
                ticket.setPriority(priority);
                User createdBy = userRepo.findByEmail(createAdminTicketDTO.getEmail())
                                .orElseThrow(() -> new UserNotFoundException(createAdminTicketDTO.getEmail()));

                ticket.setCreatedBy(createdBy);

                ticket.setCreatedAt(LocalDateTime.now());

                Ticket savedTicket = ticketRepo.save(ticket);

                TicketHistory ticketHistory = new TicketHistory();
                ticketHistory.setTicket(savedTicket);
                ticketHistory.setStatus(status);
                ticketHistory.setChangedAt(LocalDateTime.now());

                ticketHistoryRepo.save(ticketHistory);

                return savedTicket;
        }

        @Override
        public TicketResponseDTO saveTicket(CreateTicketDTO createTicketDTO) {
                Ticket ticket = new Ticket();

                ticket.setTitle(createTicketDTO.getTitle());
                ticket.setDescription(createTicketDTO.getDescription());

                User createdBy = userRepo.findByEmail(createTicketDTO.getEmail())
                                .orElseThrow(() -> new UserNotFoundException(createTicketDTO.getEmail()));

                ticket.setCreatedBy(createdBy);
                Ticket savedTicket = ticketRepo.save(ticket);

                return ticketConverter.convertToTicketResponseDTO(savedTicket);
        }

        @Transactional
        @Override
        public TicketResponseDTO updateTicket(UpdateTicketDTO updateTicketDTO) {
                Ticket ticket = ticketRepo.findById(updateTicketDTO.getTicketId())
                                .orElseThrow(() -> new TicketNotFoundException(updateTicketDTO.getTicketId()));

                if (updateTicketDTO.getTitle() != null && !updateTicketDTO.getTitle().isEmpty()) {
                        ticket.setTitle(updateTicketDTO.getTitle());
                }

                if (updateTicketDTO.getDescription() != null && !updateTicketDTO.getDescription().isEmpty()) {
                        ticket.setDescription(updateTicketDTO.getDescription());
                }

                if (updateTicketDTO.getStatusId() != null) {
                        Status newStatus = statusRepo.findById(updateTicketDTO.getStatusId())
                                        .orElseThrow(() -> new StatusNotFoundException(updateTicketDTO.getStatusId()));

                        TicketHistory statusHistory = new TicketHistory();
                        statusHistory.setTicket(ticket);
                        statusHistory.setStatus(newStatus);
                        statusHistory.setChangedAt(LocalDateTime.now());
                        ticketHistoryRepo.save(statusHistory);
                }

                if (updateTicketDTO.getPriorityId() != null) {
                        Priority newPriority = priorityRepo.findById(updateTicketDTO.getPriorityId())
                                        .orElseThrow(() -> new PriorityNotFoundException(
                                                        updateTicketDTO.getPriorityId()));
                        ticket.setPriority(newPriority);
                }

                if (updateTicketDTO.getCategoryId() != null) {
                        Category newCategory = categoryRepo.findById(updateTicketDTO.getCategoryId())
                                        .orElseThrow(() -> new CategoryNotFoundException(
                                                        updateTicketDTO.getCategoryId()));
                        ticket.setCategory(newCategory);
                }

                if (updateTicketDTO.getUserId() != null) {
                        User newAgent = userRepo.findById(updateTicketDTO.getUserId())
                                        .orElseThrow(() -> new AgentNotFoundException(updateTicketDTO.getUserId()));

                        Assignment oldAssignment = assignmentRepo.findByTicket(ticket);
                        if (oldAssignment != null) {
                                assignmentRepo.delete(oldAssignment);
                        }

                        Assignment newAssignment = new Assignment();
                        newAssignment.setTicket(ticket);
                        newAssignment.setAgent(newAgent);
                        assignmentRepo.save(newAssignment);
                }

                Ticket updatedTicket = ticketRepo.save(ticket);

                return ticketConverter.convertToTicketResponseDTO(updatedTicket);
        }

        @Transactional
        @Override
        public void deleteTicket(Long id) {
                Ticket ticket = ticketRepo.findById(id)
                                .orElseThrow(() -> new TicketNotFoundException(id));

                assignmentRepo.deleteByTicketId(id);
                ticketHistoryRepo.deleteByTicketId(id);

                ticketRepo.delete(ticket);
        }

        @Override
        public void assignAgentToTicket(Long ticketId, Long agentId) {
                Ticket ticket = ticketRepo.findById(ticketId)
                                .orElseThrow(() -> new TicketNotFoundException(ticketId));

                List<User> agents = userRepo.findByRoleId((long) 4);

                User agent = agents.stream()
                                .filter(a -> a.getId().equals(agentId))
                                .findFirst()
                                .orElseThrow(() -> new AgentNotFoundException(agentId));

                Assignment assignment = new Assignment();
                assignment.setTicket(ticket);
                assignment.setAgent(agent);
                assignment.setAssignedAt(LocalDateTime.now());

                assignmentRepo.save(assignment);
        }

        @Override
        public void changeCategoryToTicket(Long ticketId, Long categoryId) {
                Ticket ticket = ticketRepo.findById(ticketId)
                                .orElseThrow(() -> new TicketNotFoundException(ticketId));
                Category category = categoryRepo.findById(categoryId)
                                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

                ticket.setCategory(category);

                ticketRepo.save(ticket);
        }

        @Override
        public List<TicketResponseDTO> getTicketsByFilter(Long agentId, Long categoryId, String prioriyName,
                        LocalDate date, Long statusId) {
                Specification<Ticket> spec = Specification.where(null);

                if (agentId != null) {
                        spec = spec.and(TicketSpecifications.hasAgent(agentId));
                }

                if (categoryId != null) {
                        spec = spec.and(TicketSpecifications.hasCategory(categoryId));
                }

                if (prioriyName != null) {
                        spec = spec.and(TicketSpecifications.hasPriority(prioriyName));
                }

                if (date != null) {
                        spec = spec.and(TicketSpecifications.createdBefore(date));
                }

                if (statusId != null) {
                        spec = spec.and(TicketSpecifications.hasStatus(statusId));
                }

                List<Ticket> tickets = ticketRepo.findAll(spec);

                return tickets.stream()
                                .map(ticketConverter::convertToTicketResponseDTO)
                                .collect(Collectors.toList());
        }

        private String prepareQuery(String query) {
                String[] words = query.split("\\s+");

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

                Long resolvedStatusId = 5L; 
                Double averageResolutionTime = ticketRepo.findAverageResolutionTimeInHours(resolvedStatusId);

                if (averageResolutionTime != null) {
                        averageResolutionTime = Math.round(averageResolutionTime * 100.0) / 100.0;
                }

                return new TicketStatusSummaryDTO(totalTickets, statusCounts, averageResolutionTime);
        }

}
