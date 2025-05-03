package com.deskify.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketHistoryDTO {

    private Long id;
    private String statusName;
    private LocalDateTime changedAt;

}
