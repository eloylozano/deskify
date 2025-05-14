package com.deskify.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    private String email;
    private String password;
}
