package com.deskify.dto;

import java.time.LocalDateTime;

import com.deskify.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentResponseDTO {
    
    private Long id;
    private Long ticketId;
    private Long userId;
    private String userFullName;
    private String profilePictureUrl;
    private String userEmail;
    private String commentText;
    private LocalDateTime writtenOn;

    public void setUser(User user) {
        this.userFullName = user.getFirstName() + " " + user.getLastName();
        this.profilePictureUrl = user.getProfilePictureUrl();
    }

    public CommentResponseDTO(Long ticketId, Long userId, String userFullName,  String profilePictureUrl,
            String userEmail, String commentText, LocalDateTime writtenOn) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.userFullName = userFullName;
        this.profilePictureUrl = profilePictureUrl;
        this.userEmail = userEmail;
        this.commentText = commentText;
        this.writtenOn = writtenOn;
    }

    
}
