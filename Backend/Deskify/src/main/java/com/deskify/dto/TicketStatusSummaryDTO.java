package com.deskify.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketStatusSummaryDTO {
    private Long totalTickets;
    private Map<String, Long> statusCounts;
    private Double averageResolutionTime;

    
}