package com.deskify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    long countByAgentId(Long agentId);

      @Query("""
        SELECT COUNT(DISTINCT a.ticket.id)
        FROM Assignment a
        JOIN TicketHistory th ON a.ticket.id = th.ticket.id
        WHERE a.agent.id = :agentId
          AND th.status.name = 'RESUELTO'
    """)
    Long countResolvedTicketsByAgent(@Param("agentId") Long agentId);

    @Query("""
        SELECT DISTINCT a.ticket
        FROM Assignment a
        WHERE a.agent.id = :agentId
          AND a.ticket.id IN (
              SELECT th.ticket.id
              FROM TicketHistory th
              WHERE th.changedAt IN (
                  SELECT MAX(th2.changedAt)
                  FROM TicketHistory th2
                  WHERE th2.ticket.id = th.ticket.id
              )
              AND th.status.name NOT IN ('RESUELTO', 'CERRADO')
          )
    """)
    List<Ticket> findOpenTicketsByAgent(@Param("agentId") Long agentId);
}
