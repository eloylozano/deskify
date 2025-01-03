package com.deskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    Assignment findTopByTicketIdOrderByAssignedAtDesc(Long ticketId);

}
