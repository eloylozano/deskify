package com.deskify.dto;

import java.time.LocalDateTime;

public class TicketDTO {

    private Long id;

    private UserDTO user;

    private CategoryDTO category;

    private PriorityDTO priority;

    private String title;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private StatusDTO status;

    public TicketDTO() {
    }

    public TicketDTO(UserDTO user, CategoryDTO category, PriorityDTO priority, String title, String description,
            LocalDateTime createdAt, LocalDateTime updatedAt, StatusDTO status) {
        this.user = user;
        this.category = category;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public PriorityDTO getPriority() {
        return priority;
    }

    public void setPriority(PriorityDTO priority) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TicketDTO [id=" + id + ", user=" + user + ", category=" + category + ", priority=" + priority
                + ", title=" + title + ", description=" + description + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + ", status=" + status + "]";
    }

}
