package com.deskify.dto;

import com.deskify.model.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {

    private String clientName;
    private String mail;

    public void setClientName(User client) {
        this.clientName = client.getFirstName() + " " + client.getLastName();
    }

}
