package com.deskify.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Size;

public class CommentsDTO {

    private Long id;

    @JsonProperty("ticket_id")
    private TicketDTO ticket;

    @JsonProperty("user_id")
    private UserDTO user;

    @Size(min = 1, max = 500, message = "Comment must be between 1 and 500 characters")
    private String comment;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public CommentsDTO() {
    }

    public CommentsDTO(TicketDTO ticket, UserDTO user, String comment, LocalDateTime createdAt) {
        this.ticket = ticket;
        this.user = user;
        this.comment = comment;
        this.createdAt = createdAt;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CommentsDTO [id=" + id + ", ticket=" + ticket + ", user=" + user + ", comment=" + comment
                + ", createdAt=" + createdAt + "]";
    }
}
