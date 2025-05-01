package com.deskify.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deskify.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {

    List<Ticket> findByCategory_Id(Long categoryId);

    List<Ticket> findByPriority_Name(String priorityName);

    @Query(value = "SELECT * " +
            "FROM tickets t " +
            "WHERE CONCAT(t.title, ' ', t.description) REGEXP :query", nativeQuery = true)
    List<Ticket> searchByQuery(@Param("query") String query);

    @Query("""
            SELECT th.status.name, COUNT(t.id)
            FROM Ticket t
            JOIN TicketHistory th ON th.ticket = t
            WHERE th.changedAt = (
                SELECT MAX(th2.changedAt)
                FROM TicketHistory th2
                WHERE th2.ticket = t
            )
            GROUP BY th.status.name
            """)
    List<Object[]> countTicketsByCurrentStatus();
}