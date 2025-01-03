package com.deskify.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "assigments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = true)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = true)
    private User agent;

    @Column(name = "assigned_at", nullable = true, updatable = false)
    private LocalDateTime assignedAt;

    // This method will be automatically called before a new entity is persisted to
    // the database.
    @PrePersist
    protected void onCreate() {
        this.assignedAt = LocalDateTime.now();
    }

}
