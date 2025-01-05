package com.deskify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deskify.dto.CommentDTO;
import com.deskify.dto.CommentResponseDTO;
import com.deskify.service.CommentService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ticket")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/{ticketId}/comment")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long ticketId,
            @RequestBody CommentDTO commentDTO) {
        CommentResponseDTO responseDTO = commentService.createComment(ticketId, commentDTO.getUserEmail(),
                commentDTO.getCommentText());
        return ResponseEntity.ok(responseDTO);
    }

}
