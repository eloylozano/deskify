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
public class AgentAssignedDTO {

    private String profilePictureUrl;
    private String agentName;
    private String mail;


    public void SetAgentName(User agent) {
        this.agentName = agent.getFirstName() + " " + agent.getLastName();
    }
}
