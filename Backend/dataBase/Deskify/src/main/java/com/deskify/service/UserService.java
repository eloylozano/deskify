package com.deskify.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.dto.converter.UserConverter;
import com.deskify.error.UserAlreadyExists;
import com.deskify.error.UserNotFoundException;
import com.deskify.error.RoleNotFoundException;
import com.deskify.model.User;
import com.deskify.model.Role;
import com.deskify.repository.RoleRepository;
import com.deskify.repository.UserRepository;
import com.deskify.service.interfaces.IUserService;

import jakarta.transaction.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
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

    @Transactional
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> ticketList = userRepo.findAll();
        return ticketList.stream()
                .map(userConverter::convertToDTO) // Uses converter to get userDTO
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(Long id, UserResponseDTO userDTO) {

        // Finds the user
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Validate the user email
        if (userRepo.existsByEmailAndIdNot(userDTO.getEmail(), user.getId())) {
            // Throw error already exists
            throw new UserAlreadyExists("Este email ya está siendo usado: " + userDTO.getEmail());
        }

        // Validate the user phone number
        if (userRepo.existsByPhoneNumberAndIdNot(userDTO.getPhoneNumber(), user.getId())) {
            // Throw error already exists
            throw new UserAlreadyExists("Este número de teléfono ya está siendo usado: " + userDTO.getPhoneNumber());
        }

        // Update existing userDTO
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail()); 
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCompany(userDTO.getCompany());
        user.setProfilePictureUrl(userDTO.getProfilePictureUrl());

        if (userDTO.getRoleName() != null) {
            Role role = roleRepo.findByName(userDTO.getRoleName())
                    .orElseThrow(() -> new RoleNotFoundException(userDTO.getRoleName()));
            user.setRole(role);
        }

        User updatedUser = userRepo.save(user);

        // Converts user into dto and shows the updated
        return userConverter.convertToDTO(updatedUser);
    }

}
