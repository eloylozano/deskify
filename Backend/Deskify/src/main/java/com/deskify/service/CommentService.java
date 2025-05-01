package com.deskify.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.dto.CommentResponseDTO;
import com.deskify.error.CommentNotFoundException;
import com.deskify.error.TicketNotFoundException;
import com.deskify.error.UserNotFoundException;
import com.deskify.model.Comment;
import com.deskify.model.User;
import com.deskify.model.Ticket;
import com.deskify.repository.CommentRepository;
import com.deskify.repository.TicketRepository;
import com.deskify.repository.UserRepository;
import com.deskify.service.interfaces.ICommentService;

import jakarta.transaction.Transactional;

@Service
public class CommentService implements ICommentService {

        @Autowired
        private CommentRepository commentRepo;

        @Autowired
        private TicketRepository ticketRepo;

        @Autowired
        private UserRepository userRepo;

        @Override
        public CommentResponseDTO createComment(Long ticketId, String userEmail, String text) {
                // Find ticket by ID
                Ticket ticket = ticketRepo.findById(ticketId)
                                .orElseThrow(() -> new TicketNotFoundException(ticketId));
                // Find user by email
                User user = userRepo.findByEmail(userEmail)
                                .orElseThrow(() -> new UserNotFoundException(userEmail));

                // Create new comment
                Comment comment = new Comment();
                comment.setTicket(ticket);
                comment.setUser(user);
                comment.setCommentText(text);

                ticket.setUpdatedAt(LocalDateTime.now());
                ticketRepo.save(ticket);

                // Save the new comment
                Comment savedComment = commentRepo.save(comment);

                // Return commentDTO
                CommentResponseDTO responseDTO = new CommentResponseDTO(
                                savedComment.getTicket().getId(), // ticketId
                                savedComment.getUser().getId(), // userId
                                savedComment.getUser().getFirstName() + " " + savedComment.getUser().getLastName(),
                                savedComment.getUser().getEmail(),
                                savedComment.getCommentText(),
                                savedComment.getWrittenOn());

                return responseDTO;
        }

        @Transactional
        @Override
        public void deleteComment(Long commentId) {
                Comment comment = commentRepo.findById(commentId)
                                .orElseThrow(() -> new CommentNotFoundException(commentId));

                commentRepo.delete(comment);
        }

        @Transactional
        @Override
        public CommentResponseDTO updateComment(Long commentId, String text) {

                Comment comment = commentRepo.findById(commentId)
                                .orElseThrow(() -> new CommentNotFoundException(commentId));

                // Update the comment text
                comment.setCommentText(text);

                // And save it
                Comment updatedComment = commentRepo.save(comment);

                // Convert the updated comment to a JSON representation
                CommentResponseDTO commentDTO = new CommentResponseDTO();
                commentDTO.setUserFullName(
                                updatedComment.getUser().getFirstName() + " " + updatedComment.getUser().getLastName());
                commentDTO.setUserEmail(updatedComment.getUser().getEmail());
                commentDTO.setCommentText(updatedComment.getCommentText());
                commentDTO.setWrittenOn(updatedComment.getWrittenOn());

                return commentDTO;
        }

}
