package com.deskify.specs;

import com.deskify.model.Assignment;
import com.deskify.model.Ticket;
import com.deskify.model.TicketHistory;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

public class TicketSpecifications {

    public static Specification<Ticket> hasAgent(Long agentId) {
        return (root, query, criteriaBuilder) -> {
            Join<Ticket, Assignment> assignmentsJoin = root.join("assignments", JoinType.INNER);
            return criteriaBuilder.equal(assignmentsJoin.get("agent").get("id"), agentId);
        };
    }

    public static Specification<Ticket> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    public static Specification<Ticket> hasPriority(String priorityName) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("priority").get("name"), priorityName);
        };
    }

    public static Specification<Ticket> createdBefore(LocalDate fecha) {
        return (root, query, criteriaBuilder) -> {
            if (fecha == null) {
                return criteriaBuilder.conjunction(); // Without filter if date is null
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), fecha);
        };
    }

    public static Specification<Ticket> hasStatus(Long statusId) {
        return (root, query, criteriaBuilder) -> {
            // Join two tables with the same status
            Join<Ticket, TicketHistory> historyJoin = root.join("ticketHistory", JoinType.INNER);

            // Fiilter by status Name
            return criteriaBuilder.equal(historyJoin.get("status").get("id"), statusId);
        };
    }

}