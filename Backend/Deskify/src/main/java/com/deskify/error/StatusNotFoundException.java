package com.deskify.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException(String name){
        super("No existe este estado: " + name);
    }

    
    public StatusNotFoundException(Long id) {
        super("Status no encontrado con ID: " + id);
    }
}
