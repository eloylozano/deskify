package com.deskify.dto;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    private String comment;
    private String userName; 
    private LocalDateTime createdAt;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String comment, String userName, LocalDateTime createdAt) {
        this.id = id;
        this.comment = comment;
        this.userName = userName;
        this.createdAt = createdAt;
    }


    public Long getCommentId() {
        return id;
    }

    public void setCommentId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}