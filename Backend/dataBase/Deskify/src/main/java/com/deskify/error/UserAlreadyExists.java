package com.deskify.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists(String email) {
        super("Este email ya está siendo usado: " + email);
    }
}
