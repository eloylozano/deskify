package com.deskify.dto;

public class CommentCreateDTO {

    private Long ticketId; 
    private String comment; 

    public CommentCreateDTO() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentCreateDTO [ticketId=" + ticketId + ", comment=" + comment + "]";
    }

}
