package com.deskify.dto;

public class PriorityDTO {

    private String name;

    private String description;

    public PriorityDTO() {
    }

    public PriorityDTO(String name, String description) {
        this.name = name;
        this.description = description;
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
        return "PriorityDTO [name=" + name + ", description=" + description + "]";
    }

}
