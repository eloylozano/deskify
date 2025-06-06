package com.deskify.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriorityNotFoundException extends RuntimeException {

    public PriorityNotFoundException(String name){
        super("No existe esta prioridad: " + name);
    }

    public PriorityNotFoundException(Long id) {
        super("Prioridad no encontrada con ID: " + id);
    }
}
