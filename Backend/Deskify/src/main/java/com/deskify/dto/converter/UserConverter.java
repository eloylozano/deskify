package com.deskify.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.deskify.dto.AgentDTO;
import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.SubscriptionDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.model.Subscription;
import com.deskify.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper mapper;

    public UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = mapper.map(user, UserResponseDTO.class);

        // Buscar suscripción activa
        Subscription activeSub = user.getSubscriptions().stream()
                .filter(Subscription::isActive)
                .findFirst()
                .orElse(null);

        if (activeSub != null) {
            SubscriptionDTO subDTO = mapper.map(activeSub, SubscriptionDTO.class);
            dto.setActiveSubscription(subDTO);
        }

        return dto;
    }

    public AgentDTO UserTodAgentDTO(User user) {
        return mapper.map(user, AgentDTO.class);
    }

    public User convertToEntity(CreateUserDTO createUserDTO) {
        return mapper.map(createUserDTO, User.class);
    }
}
