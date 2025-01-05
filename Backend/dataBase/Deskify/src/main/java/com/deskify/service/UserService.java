package com.deskify.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.dto.converter.UserConverter;
import com.deskify.error.UserAlreadyExists;
import com.deskify.model.User;
import com.deskify.repository.UserRepository;
import com.deskify.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    UserConverter userConverter;

    @Override
    public UserResponseDTO createUser(CreateUserDTO createUser) {
        // Validate the user
        if (userRepo.existsByEmail(createUser.getEmail())) {
            // Throw error already exists
            throw new UserAlreadyExists(createUser.getEmail());
        }

        // Create a new user
        User newUser = new User();
        newUser.setFirstName(createUser.getFirstName());
        newUser.setLastName(createUser.getLastName());
        newUser.setEmail(createUser.getEmail());
        newUser.setPassword(createUser.getPassword());

        // Save the user
        User savedUser = userRepo.save(newUser);

        // Convert user to dto
        return userConverter.convertToDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> ticketList = userRepo.findAll();
        return ticketList.stream()
                .map(userConverter::convertToDTO) // Uses converter to get userDTO
                .collect(Collectors.toList());
    }

}
