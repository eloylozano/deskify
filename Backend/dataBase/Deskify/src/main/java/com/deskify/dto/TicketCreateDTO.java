package com.deskify.dto;

public class TicketCreateDTO {

    private String userName;
    private String category;
    private String priority;
    private String title;
    private String description;

    public TicketCreateDTO() {
    }

    public TicketCreateDTO(String userName, String category, String priority, String title, String description) {
        this.userName = userName;
        this.category = category;
        this.priority = priority;
        this.title = title;
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
