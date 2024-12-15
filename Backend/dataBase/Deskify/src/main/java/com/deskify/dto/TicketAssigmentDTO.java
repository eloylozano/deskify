package com.deskify.dto;

import java.time.LocalDateTime;

public class TicketAssigmentDTO {

    private Long id;

    private TicketDTO ticket;

    private UserDTO agent;

    private LocalDateTime assignmentAt;

    public TicketAssigmentDTO() {
    }

    public TicketAssigmentDTO(TicketDTO ticket, UserDTO agent, LocalDateTime assignmentAt) {
        this.ticket = ticket;
        this.agent = agent;
        this.assignmentAt = assignmentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketDTO getTicket() {
        return ticket;
    }

    public void setTicket(TicketDTO ticket) {
        this.ticket = ticket;
    }

    public UserDTO getAgent() {
        return agent;
    }

    public void setAgent(UserDTO agent) {
        this.agent = agent;
    }

    public LocalDateTime getAssignmentAt() {
        return assignmentAt;
    }

    public void setAssignmentAt(LocalDateTime assignmentAt) {
        this.assignmentAt = assignmentAt;
    }

    @Override
    public String toString() {
        return "TicketAssigmentDTO [id=" + id + ", ticket=" + ticket + ", agent=" + agent + ", assignmentAt="
                + assignmentAt + "]";
    }

}
