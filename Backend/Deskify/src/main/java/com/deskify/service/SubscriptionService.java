package com.deskify.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.dto.SubscriptionDTO;
import com.deskify.dto.converter.SubscriptionConverter;
import com.deskify.error.PlanNotFoundException;
import com.deskify.error.SubscriptionNotFoundException;
import com.deskify.error.UserNotFoundException;
import com.deskify.model.Plan;
import com.deskify.model.Subscription;
import com.deskify.model.User;
import com.deskify.repository.PlanRepository;
import com.deskify.repository.SubscriptionRepository;
import com.deskify.repository.UserRepository;
import com.deskify.service.interfaces.ISubscriptionService;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PlanRepository planRepo;
    @Autowired
    private SubscriptionConverter subscriptionConverter;

    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        User user = userRepo.findByEmail(subscriptionDTO.getUser().getEmail())
                .orElseThrow(() -> new UserNotFoundException(subscriptionDTO.getUser().getEmail()));

        Plan plan = planRepo.findByName(subscriptionDTO.getPlan().getName())
                .orElseThrow(() -> new PlanNotFoundException(subscriptionDTO.getPlan().getName()));

        LocalDateTime startDate = LocalDateTime.now(); 
        LocalDateTime endDate = startDate.plusDays(plan.getDuration()); 

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDateTime(startDate);
        subscription.setEndDateTime(endDate);
        subscription.setActive(true);

        subscription = subscriptionRepo.save(subscription);
        return subscriptionConverter.convertToDTO(subscription);
    }

    @Override
    public SubscriptionDTO updateSubscription(Long subscriptionId, boolean isActive) {
        Subscription subscription = subscriptionRepo.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada"));

        subscription.setActive(isActive);
        return subscriptionConverter.convertToDTO(subscription);

    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subsList = subscriptionRepo.findAll();
        return subsList.stream()
                .map(subscriptionConverter::convertToDTO) 
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO getSubscriptionById(Long id) {
        Subscription sub = subscriptionRepo.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));

        return subscriptionConverter.convertToDTO(sub);
    }
}