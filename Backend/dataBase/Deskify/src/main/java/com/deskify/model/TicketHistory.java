package com.deskify.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "ticket_status_history")
public class TicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    public TicketHistory() {
    }

    public TicketHistory(Ticket ticket, Status status, LocalDateTime changedAt) {
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
        return "TicketHistory [id=" + id + ", ticket=" + ticket + ", status=" + status + ", changedAt=" + changedAt + "]";
    }

}
