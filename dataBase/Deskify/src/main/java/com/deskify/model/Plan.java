package com.deskify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 55, unique = true)
    private String name;

    @Column(name = "description", length = 255, columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = true)
    private Double price;

    @Column(name = "duration", length = 3, nullable = true)
    private int duration;

    public Plan() {
    }

    public Plan(String name, String description, Double price, int duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Plan [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", duration="
                + duration + "]";
    }

}
