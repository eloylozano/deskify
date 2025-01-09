package com.deskify.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.deskify.dto.AgentDTO;
import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper mapper;

    public UserResponseDTO convertToDTO(User user) {
        return mapper.map(user, UserResponseDTO.class);
    }

    public AgentDTO UserTodAgentDTO(User user) {
        return mapper.map(user, AgentDTO.class);
    }


    public User convertToEntity(CreateUserDTO createUserDTO) {
        return mapper.map(createUserDTO, User.class);
    }
}
