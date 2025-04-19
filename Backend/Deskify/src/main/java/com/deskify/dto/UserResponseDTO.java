package com.deskify.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {
    
    private Long id;
    private String profilePictureUrl;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String company;
    private String roleName;
    private SubscriptionDTO activeSubscription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
