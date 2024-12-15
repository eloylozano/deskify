package com.deskify.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private Plan plan;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date", nullable = false, updatable = false)
    private LocalDateTime endDateTime;

    @Column(name = "is_Active", nullable = false)
    private boolean isActive;

    public Subscription() {
    }

    public Subscription(User user, Plan plan) {
        this.user = user;
        this.plan = plan;
        this.isActive = true; // Supossing the plan stars as active
    }

    // This method is called before persisting the entity. 
    @PrePersist
    private void prePersist() {
        if (this.startDateTime == null) {
            this.startDateTime = LocalDateTime.now();
        }
        if (this.endDateTime == null && this.plan != null) {
            // Assuming the plan duration is in days
            this.endDateTime = this.startDateTime.plusDays(this.plan.getDuration());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Subscription [id=" + id + ", user=" + user + ", plan=" + plan + ", startDateTime=" + startDateTime
                + ", endDateTime=" + endDateTime + ", isActive=" + isActive + "]";
    }

}