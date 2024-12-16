package com.deskify.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentResponseDTO {

    private Long commentId;
    private String userName;
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public CommentResponseDTO() {
    }

    public CommentResponseDTO(Long commentId, String userName, String comment, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.userName = userName;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CommentResponseDTO [commentId=" + commentId + ", userName=" + userName + ", comment=" + comment + ", createdAt=" + createdAt + "]";
    }
}
