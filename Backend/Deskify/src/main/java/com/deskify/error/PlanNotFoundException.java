package com.deskify.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlanNotFoundException extends RuntimeException {

    public PlanNotFoundException(Long id){
        super("No existe ningún plan con este id: " + id);
    }

    public PlanNotFoundException(String plan){
        super("No existe ningún plan con este nombre: " + plan);
    }

}
