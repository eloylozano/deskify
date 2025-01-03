package com.deskify.dto;

import java.time.LocalDateTime;
import java.util.List;

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
    private TicketHistoryDTO currentStatus;
    private ClientDTO client;
    private AgentAssignedDTO agent;
    private String title;
    private String description;
    private List<CommentDTO> comments; // List of comments written
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
}