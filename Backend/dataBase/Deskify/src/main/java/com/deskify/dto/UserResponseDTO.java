package com.deskify.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String roleName;
    private LocalDateTime createdAt;

    public UserResponseDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserResponseDTO(String firstName, String lastName, String email, String roleName, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roleName = roleName;
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserResponseDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", roleName=" + roleName + ", createdAt=" + createdAt + "]";
    }

}
