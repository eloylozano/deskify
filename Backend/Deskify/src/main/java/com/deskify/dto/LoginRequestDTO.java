package com.deskify.dto;
import lombok.*;

@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}
