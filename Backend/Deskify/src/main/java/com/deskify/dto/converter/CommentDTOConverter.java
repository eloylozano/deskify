package com.deskify.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.deskify.dto.CommentDTO;
import com.deskify.model.Comment;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommentDTOConverter {

    private final ModelMapper mapper;

    public CommentDTO convertToDTO(Comment comment) {
        return mapper.map(comment, CommentDTO.class);
    }
}
