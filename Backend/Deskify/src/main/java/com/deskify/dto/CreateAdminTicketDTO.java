package com.deskify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateAdminTicketDTO {
    private String title;
    private String description;
    private String email;
    private Long statusId;
    private Long categoryId;
    private Long priorityId;
}
