package com.deskify.service.interfaces;

import java.util.List;

import com.deskify.dto.SubscriptionDTO;

public interface ISubscriptionService {

    public SubscriptionDTO updateSubscription(Long subscriptionId, boolean isActive);

    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);

    public List<SubscriptionDTO> getAllSubscriptions();

    public SubscriptionDTO getSubscriptionById(Long id);
}
