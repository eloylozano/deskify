package com.deskify.dto;

public class TicketAssignmentDTO {

    private Long ticketId;
    private Long agentId;

    public TicketAssignmentDTO(Long ticketId, Long agentId) {
        this.ticketId = ticketId;
        this.agentId = agentId;
    }

    public TicketAssignmentDTO() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

}
