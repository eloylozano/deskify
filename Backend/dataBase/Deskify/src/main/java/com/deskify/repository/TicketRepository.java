package com.deskify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.deskify.model.Ticket;
import com.deskify.model.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {

    List<Ticket> findByCategoryId(Long categoryId);

    List<Ticket> findByPriorityId(Long priorityId);
    
}