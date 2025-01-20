package com.deskify.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.deskify.dto.SubscriptionDTO;
import com.deskify.model.Subscription;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubscriptionConverter {
    private final ModelMapper mapper;

    public SubscriptionDTO convertToDTO(Subscription subscription) {
        return mapper.map(subscription, SubscriptionDTO.class);
    }
}
