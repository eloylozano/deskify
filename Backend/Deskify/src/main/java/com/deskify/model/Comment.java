package com.deskify.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    @JsonBackReference 
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference 
    private User user;

    @Column(name = "comment", length = 255, columnDefinition = "TEXT")
    private String commentText;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime writtenOn;

    // This method is automatically called before inserting a new comment into the database.
    @PrePersist
    protected void onCreate() {
        this.writtenOn = LocalDateTime.now();
    }

}
