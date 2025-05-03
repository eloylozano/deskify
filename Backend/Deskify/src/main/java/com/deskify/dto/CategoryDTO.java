package com.deskify.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;

}
