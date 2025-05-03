package com.deskify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.model.Priority;
import com.deskify.model.Ticket;
import com.deskify.repository.PriorityRepository;
import com.deskify.service.interfaces.IPriorityService;

@Service
public class PriorityService implements IPriorityService {

    @Autowired
    PriorityRepository priorityRepo;

    @Override
    public List<Priority> getAllPriorities() {
        List<Priority> list = priorityRepo.findAll();
        return list;
    }

}
