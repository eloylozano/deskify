package com.deskify.dto;

public class TicketUpdateDTO {

    private String title;
    private String description;
    private String status;
    private String category;
    private String priority;

    public TicketUpdateDTO() {
    }

    public TicketUpdateDTO(String category, String priority, String title, String description, String status) {
        this.category = category;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
