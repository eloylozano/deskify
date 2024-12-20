package com.deskify.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(String firstName, String middleName, String lastName, String phoneNumber, String email, String password,
            Role role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password + ", role=" + role
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
