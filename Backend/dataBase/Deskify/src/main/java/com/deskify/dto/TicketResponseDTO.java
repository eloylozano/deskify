package com.deskify.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TicketResponseDTO {

    private Long ticketId;
    private String category;
    private String priority;
    private String title;
    private String description;
    private LocalDateTime updatedAt;
    private List<CommentResponseDTO> comments;

    public TicketResponseDTO() {
    }

    public TicketResponseDTO(Long ticketId, String userName, String category, String priority, String title,
            String description, String status, LocalDateTime updatedAt, List<CommentResponseDTO> comments) {
        this.ticketId = ticketId;
        this.category = category;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<CommentResponseDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseDTO> comments) {
        this.comments = comments;
    }

}
