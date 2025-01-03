package com.deskify.dto;

import com.deskify.model.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AgentAssignedDTO {

    private String agentName;
    private String mail;


    public void SetAgentName(User agent) {
        this.agentName = agent.getFirstName() + " " + agent.getLastName();
    }
}
