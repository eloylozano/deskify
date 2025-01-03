package com.deskify.dto;

import java.time.LocalDateTime;
import java.util.List;

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
public class TicketResponseDTO {

    private Long id;
    private CategoryDTO category;
    private PriorityDTO priority;
    private String statusName;
    private String clientName;
    private String agentName;
    private String title;
    private String description;
    private List<CommentDTO> comments; // List of comments written
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setClientName(User client) {
        this.clientName = client.getFirstName() + " " + client.getLastName();
    }

    public void SetAgentName(User agent) {
        this.agentName = agent.getFirstName() + " " + agent.getLastName();
    }
}