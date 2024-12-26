package com.deskify.dto;

import java.time.LocalDateTime;

import com.deskify.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CommentDTO {

    private Long ticketId;
    private String userFullName;
    private String commentText;
    private LocalDateTime writtenAt;

    public void setUser(User user) {
        this.userFullName = user.getFirstName() + " " + user.getLastName();
    }
}
