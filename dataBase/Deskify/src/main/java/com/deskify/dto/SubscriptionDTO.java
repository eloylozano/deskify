package com.deskify.dto;

import java.time.LocalDateTime;

public class SubscriptionDTO {

    private Long id; 

    private String planName; 

    private LocalDateTime startDate; 

    private LocalDateTime endDate; 

    private String status; 

    private String description; 

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Long id, String planName, LocalDateTime startDate, LocalDateTime endDate, String status, String description) {
        this.id = id;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.description = description;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
