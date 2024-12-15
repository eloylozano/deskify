package com.deskify.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionDTO {

    private Long id;
    
    @JsonProperty("user_id")
    private UserDTO user;

    @JsonProperty("plan_id")
    private PlanDTO plan;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;
    
    @JsonProperty("is_active")
    private Boolean active;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(UserDTO user, PlanDTO plan, LocalDateTime startDate, LocalDateTime endDate, Boolean active) {
        this.user = user;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
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

    public PlanDTO getPlan() {
        return plan;
    }

    public void setPlan(PlanDTO plan) {
        this.plan = plan;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SubscriptionDTO [id=" + id + ", user=" + user + ", plan=" + plan + ", startDate=" + startDate
                + ", endDate=" + endDate + ", active=" + active + "]";
    }

}
