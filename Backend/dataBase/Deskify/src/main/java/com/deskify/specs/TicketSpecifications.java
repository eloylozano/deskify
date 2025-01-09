package com.deskify.specs;

import com.deskify.model.Ticket;

import org.springframework.data.jpa.domain.Specification;

public class TicketSpecifications {

    public static Specification<Ticket> hasAgent(String agentName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("agent").get("agentName"), agentName);
    }
}