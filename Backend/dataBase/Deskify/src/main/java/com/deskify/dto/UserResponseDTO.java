package com.deskify.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String pictureUrl;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String firstName, String middleName, String lastName, String phoneNumber, String email,
            String pictureUrl, String roleName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.roleName = roleName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserResponseDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", phoneNumber=" + phoneNumber + ", email=" + email + ", pictureUrl=" + pictureUrl + ", roleName="
                + roleName + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
