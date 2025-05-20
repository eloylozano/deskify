package com.deskify.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubscriptionDTO {
    
    private UserResponseDTO user;
    private PlanDTO plan;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean isActive;

}
