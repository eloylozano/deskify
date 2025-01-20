package com.deskify.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketHistoryDTO {

    private String statusName;
    private LocalDateTime changedAt;

}
