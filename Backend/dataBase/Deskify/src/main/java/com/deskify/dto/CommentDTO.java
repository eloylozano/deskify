package com.deskify.dto;

import java.time.LocalDateTime;

import com.deskify.model.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {

    private Long ticketId;
    private String userFullName;
    private String userEmail;
    private String commentText;
    private LocalDateTime writtenOn;

    public void setUser(User user) {
        this.userFullName = user.getFirstName() + " " + user.getLastName();
    }
}
