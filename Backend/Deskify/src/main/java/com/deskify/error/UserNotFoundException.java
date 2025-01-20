package com.deskify.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("Usuario no encontrado con el email: " + email);
    }

    public UserNotFoundException(Long id) {
        super("Usuario no encontrado con el id: " + id);
    }
}
