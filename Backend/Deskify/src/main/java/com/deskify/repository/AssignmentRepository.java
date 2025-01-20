package com.deskify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Assignment;
import com.deskify.model.Ticket;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Assignment findTopByTicketIdOrderByAssignedAtDesc(Long ticketId);

    Optional<Ticket> findByTicketId(Long id);

    Assignment findByTicket(Ticket ticket);

    List<Assignment> findByTicketIdOrderByAssignedAtDesc(Long ticketId);

    void deleteByTicketId(Long id);


}
