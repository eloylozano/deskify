package com.deskify.dto;

import jakarta.validation.constraints.Email;

public class UserDTO {

    private String firstName;

    private String middleName;

    private String lastName;

    private String phoneNumber;

    @Email(message = "Email should be valid")
    private String email;

    private String roleName;

    public UserDTO() {
    }

    public UserDTO(String firstName, String middleName, String lastName, String phoneNumber,
            @Email(message = "Email should be valid") String email, String roleName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserDTO [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", phoneNumber=" + phoneNumber + ", email=" + email + ", roleName=" + roleName + "]";
    }

}
