package com.deskify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deskify.model.Ticket;
import com.deskify.model.TicketHistory;

@Repository
public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {

    List<TicketHistory> findByTicketIdOrderByChangedAtDesc(Long ticketId);

    TicketHistory findByTicket(Ticket ticket);

    void deleteByTicketId(Long id);
  @Query("SELECT th FROM TicketHistory th WHERE th.ticket.id = :ticketId ORDER BY th.changedAt DESC")
    List<TicketHistory> findLatestStatusByTicketId(@Param("ticketId") Long ticketId);
}
