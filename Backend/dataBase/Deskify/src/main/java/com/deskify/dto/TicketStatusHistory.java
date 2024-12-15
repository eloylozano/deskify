package com.deskify.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketStatusHistory {

    private Long id;

    @JsonProperty("ticket_id")
    private TicketDTO ticket;

    private StatusDTO status;

    @JsonProperty("changed_at")
    private LocalDateTime changedAt;

    public TicketStatusHistory() {
    }

    public TicketStatusHistory(TicketDTO ticket, StatusDTO status, LocalDateTime changedAt) {
        this.ticket = ticket;
        this.status = status;
        this.changedAt = changedAt;
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

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    @Override
    public String toString() {
        return "TicketStatusHistory [id=" + id + ", ticket=" + ticket + ", status=" + status + ", changedAt="
                + changedAt + "]";
    }

}
