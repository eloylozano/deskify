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

    private String userEmail;
    private String commentText;

}
