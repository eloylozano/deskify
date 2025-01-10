package com.deskify.specs;

import com.deskify.model.Assignment;
import com.deskify.model.Ticket;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

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

    public static Specification<Ticket> hasPriority(Long priorityId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("priority").get("id"), priorityId);
        };
    }
}