package com.deskify.dto;

public class StatusResponseDTO {

    private Long statusId;
    private String name;
    private String description;

    public StatusResponseDTO() {

    }

    public StatusResponseDTO(Long statusId, String name, String description) {
        this.statusId = statusId;
        this.name = name;
        this.description = description;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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
        return "StatusResponseDTO [statusId=" + statusId + ", name=" + name + ", description=" + description + "]";
    }
}
