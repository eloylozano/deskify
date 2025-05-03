package com.deskify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.model.Priority;
import com.deskify.model.Status;
import com.deskify.repository.StatusRepository;
import com.deskify.service.interfaces.IStatusService;

@Service
public class StatusService implements IStatusService {

    @Autowired
    StatusRepository statusRepo;

    @Override
    public List<Status> getAllStatus() {
        List<Status> list = statusRepo.findAll();
        return list;
    }

}
