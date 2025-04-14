package com.deskify.dto;

import com.deskify.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {
    private String profilePictureUrl;
    private String clientName;
    private String mail;

    public void setClientName(User client) {
        this.clientName = client.getFirstName() + " " + client.getLastName();
    }

}
