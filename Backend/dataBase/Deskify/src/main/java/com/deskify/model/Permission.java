package com.deskify.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "permissions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 55, unique = true)
    private String name;

    @Column(name = "description", length = 255, columnDefinition = "TEXT")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;

}
