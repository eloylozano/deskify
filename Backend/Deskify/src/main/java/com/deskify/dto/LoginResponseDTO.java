package com.deskify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private Long userId;
    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String company;
    private String profilePictureUrl;
    private String roleName;  // El rol del usuario (ejemplo: "USER" o "ADMIN")
}
