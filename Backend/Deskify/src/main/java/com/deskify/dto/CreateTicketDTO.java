package com.deskify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateTicketDTO {

    private String title;
    private String description;
    private String email;
}
