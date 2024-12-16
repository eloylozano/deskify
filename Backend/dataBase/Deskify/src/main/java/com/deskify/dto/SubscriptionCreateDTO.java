package com.deskify.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;

public class SubscriptionCreateDTO {

    @Email
    private String userMail;
    private String planName;
    private LocalDateTime startDate;

    public SubscriptionCreateDTO() {
    }

    public SubscriptionCreateDTO(String userMail, String planName, LocalDateTime startDate) {
        this.userMail = userMail;
        this.planName = planName;
        this.startDate = startDate;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
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

    @Override
    public String toString() {
        return "SubscriptionCreateDTO [userMail=" + userMail + ", planName=" + planName + ", startDate=" + startDate
                + "]";
    }

}
