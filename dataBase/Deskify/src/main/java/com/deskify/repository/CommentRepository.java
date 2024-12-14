package com.deskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deskify.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
