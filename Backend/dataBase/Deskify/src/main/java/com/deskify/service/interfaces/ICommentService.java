package com.deskify.service.interfaces;

import com.deskify.dto.CommentResponseDTO;

public interface ICommentService {

    public CommentResponseDTO createComment(Long ticketId, String userEmail, String text);

    public void deleteComment(Long commentId);

    public CommentResponseDTO updateComment(Long commentId, String text);


}
