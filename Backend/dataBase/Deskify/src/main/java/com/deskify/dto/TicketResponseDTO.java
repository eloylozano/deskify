package com.deskify.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TicketResponseDTO {

    private Long ticketId;
    private String category;
    private String priority;
    private String title;
    private String description;
    private LocalDateTime updatedAt;
    private List<CommentResponseDTO> comments;

    public TicketResponseDTO(Long ticketId, String userName, String category, String priority, String title,
            String description, String status, LocalDateTime updatedAt, List<CommentResponseDTO> comments) {
        this.ticketId = ticketId;
        this.category = category;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.updatedAt = updatedAt;
        this.comments = comments;
    }


}
