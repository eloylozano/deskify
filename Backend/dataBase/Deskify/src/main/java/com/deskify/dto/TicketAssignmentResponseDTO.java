package com.deskify.dto;

public class TicketAssignmentResponseDTO {

    private Long assigmentId;
    private Long ticketId;
    private String agentName;

    public TicketAssignmentResponseDTO() {
    }

    public TicketAssignmentResponseDTO(Long assigmentId, Long ticketId, String agentName) {
        this.assigmentId = assigmentId;
        this.ticketId = ticketId;
        this.agentName = agentName;
    }

    public Long getAssigmentId() {
        return assigmentId;
    }

    public void setAssigmentId(Long assigmentId) {
        this.assigmentId = assigmentId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

}
