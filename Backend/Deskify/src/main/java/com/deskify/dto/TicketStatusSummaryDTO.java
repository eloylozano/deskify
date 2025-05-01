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

    public TicketStatusSummaryDTO(long totalTickets, Map<String, Long> statusCounts) {
        this.totalTickets = totalTickets;
        this.statusCounts = statusCounts;
    }
    
}