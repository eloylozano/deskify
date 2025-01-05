package com.deskify.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", length = 55)
    private String firstName;

    @Column(name = "middle_name", length = 55, nullable = true)
    private String middleName;

    @Column(name = "last_name", length = 55)
    private String lastName;

    @Column(name = "phone_number", length = 15, nullable = true, unique = true)
    private String phoneNumber;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "company", length = 100, nullable = true)
    private String company;

    @Value("${profile.pictures.path}") // Allows access to the file where the photos are stored
    @Column(name = "profile_picture_url", length = 255, nullable = true)
    private String profilePictureUrl;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    // This method is automatically called before persisting the entity
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // This method is automatically called before updating the entity
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
