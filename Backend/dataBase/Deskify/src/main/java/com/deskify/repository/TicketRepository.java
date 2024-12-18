package com.deskify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    List<Ticket> findByUserId(Long userId);

    List<Ticket> findByCategoryId(Long categoryId);

    List<Ticket> findByPriorityId(Long priorityId);

    List<Ticket> findByStatusId(Long statusId);
}