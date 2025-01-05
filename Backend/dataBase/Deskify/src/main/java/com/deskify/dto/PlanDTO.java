package com.deskify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlanDTO {

    private String name;
    private String description;
    private double price;
    private int durationInDays;
}
