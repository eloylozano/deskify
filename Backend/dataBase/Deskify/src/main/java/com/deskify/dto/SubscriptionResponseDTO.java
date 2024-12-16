package com.deskify.dto;

import java.time.LocalDateTime;

public class SubscriptionResponseDTO {

    private Long userId;
    private String userName;
    private String planName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isActive;

    public SubscriptionResponseDTO() {
    }

    public SubscriptionResponseDTO(String userName, String planName, LocalDateTime startDate, LocalDateTime endDate,
            Boolean isActive) {
        this.userName = userName;
        this.planName = planName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "SubscriptionResponseDTO [userId=" + userId + ", userName=" + userName + ", planName=" + planName
                + ", startDate=" + startDate + ", endDate=" + endDate + ", isActive=" + isActive + "]";
    }

}
