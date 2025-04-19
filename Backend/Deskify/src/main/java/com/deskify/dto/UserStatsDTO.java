package com.deskify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserStatsDTO {
    private long ticketsAsignados;
    private long ticketsResueltos;
    private long ticketsAbiertos;
}
