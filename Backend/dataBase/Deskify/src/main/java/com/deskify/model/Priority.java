package com.deskify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "priorities")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "description", length = 255, columnDefinition = "TEXT")
    private String description;

}
