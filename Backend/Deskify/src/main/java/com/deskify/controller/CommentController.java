package com.deskify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deskify.dto.CommentDTO;
import com.deskify.dto.CommentResponseDTO;
import com.deskify.dto.CommentTextRequestDTO;
import com.deskify.service.CommentService;

import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId,
            @RequestBody CommentTextRequestDTO request) {

        String text = request.getText(); // Get text from comment
        CommentResponseDTO updatedComment = commentService.updateComment(commentId, text);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
