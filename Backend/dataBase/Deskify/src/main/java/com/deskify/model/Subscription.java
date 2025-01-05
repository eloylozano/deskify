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
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    @JsonBackReference
    private Plan plan;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date", nullable = false, updatable = false)
    private LocalDateTime endDateTime;

    @Column(name = "is_Active", nullable = false)
    private boolean isActive;

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

}