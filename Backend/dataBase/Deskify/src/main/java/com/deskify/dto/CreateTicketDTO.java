package com.deskify.dto;

import java.time.LocalDateTime;

import com.deskify.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CreateTicketDTO {

    private String categoryName;
    private String priorityName;
    private String statusName;
    private String clientName;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    
    public void setClientName(User client) {
        this.clientName = client.getFirstName() + " " + client.getLastName();
    }
}
