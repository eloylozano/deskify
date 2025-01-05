package com.deskify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketDTO {
    private Long ticketId;
    private String statusName;
    private String priorityName;
    private Long categoryId;
    private String agentEmail;
}