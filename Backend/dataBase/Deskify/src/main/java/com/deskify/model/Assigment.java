package com.deskify.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "assigments")
public class Assigment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private User agent;

    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt;

    public Assigment() {
    }

    public Assigment(Ticket ticket, User agent) {
        this.ticket = ticket;
        this.agent = agent;
    }

    @PrePersist
    protected void onCreate() {
        this.assignedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket_id() {
        return ticket;
    }

    public void setTicket_id(Ticket ticket_id) {
        this.ticket = ticket_id;
    }

    public User getAgent_id() {
        return agent;
    }

    public void setAgent_id(User agent_id) {
        this.agent = agent_id;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    @Override
    public String toString() {
        return "Assigment [id=" + id + ", ticket=" + ticket + ", agent=" + agent + ", assignedAt=" + assignedAt + "]";
    }

    

}
