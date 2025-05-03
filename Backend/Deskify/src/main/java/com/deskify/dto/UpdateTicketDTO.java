package com.deskify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketDTO {
    private Long ticketId;
    private String title;
    private String description;
    private Long statusId;
    private Long priorityId;
    private Long categoryId;
    private Long userId;
}