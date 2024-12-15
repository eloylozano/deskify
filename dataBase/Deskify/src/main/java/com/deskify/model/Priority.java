package com.deskify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "priorities")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "description", length = 255, columnDefinition = "TEXT")
    private String description;

    public Priority() {
    }

    public Priority(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Priority [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

}
