package com.deskify.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AgentNotFoundException extends RuntimeException {

    public AgentNotFoundException(String email){
        super("No existe el agente con este email: " + email);
    }

}
