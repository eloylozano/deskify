package com.deskify.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.deskify.dto.*;
import com.deskify.dto.converter.UserConverter;
import com.deskify.error.*;
import com.deskify.model.User;
import com.deskify.model.Role;
import com.deskify.model.Ticket;
import com.deskify.repository.*;
import com.deskify.service.interfaces.IUserService;

import jakarta.transaction.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    CommentRepository commentRepo;
    @Autowired
    SubscriptionRepository subscriptionRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    UserConverter userConverter;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    AssignmentRepository assignmentRepository;

    @Value("${profile.pictures.path}")
    private String profilePicturesPath;

    @Override
    public UserResponseDTO getUserById(Long id) {
        // Encuentra al usuario por id
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)); // Throw exception if user not found

        // Conver to DTO
        return userConverter.convertToDTO(user);
    }

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
        List<User> userList = userRepo.findAll();
        return userList.stream()
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

        // Validate the role name
        if (userDTO.getRoleName() != null) {
            Role role = roleRepo.findByName(userDTO.getRoleName())
                    .orElseThrow(() -> new RoleNotFoundException(userDTO.getRoleName()));
            user.setRole(role);
        }

        User updatedUser = userRepo.save(user);

        // Converts user into dto and shows the updated
        return userConverter.convertToDTO(updatedUser);
    }

    @Override
    public UserResponseDTO uploadProfilePicture(Long userId, MultipartFile file) {
        try {
            // Verify that the file is not empty
            if (file.isEmpty()) {
                throw new RuntimeException("No se ha seleccionado ningún archivo para subir.");
            }

            // Create file name
            String fileName = userId + "_" + file.getOriginalFilename();

            // File path to upload
            Path path = Paths.get(profilePicturesPath + fileName);

            // Save the file to the path
            Files.write(path, file.getBytes());

            // Get the user and update the profile picture
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Update the profile picture
            user.setProfilePictureUrl(path.toString());

            // Save the user updated
            userRepo.save(user);

            // Convert to DTO
            return userConverter.convertToDTO(user);

        } catch (IOException e) {
            throw new RuntimeException("Error al subir el archivo: " + e.getMessage());
        }
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        List<User> userList = userRepo.findByRoleId((long) 4);
        return userList.stream()
                .map(userConverter::UserTodAgentDTO) // Uses converter to get userDTO
                .collect(Collectors.toList());
    }
    public Long getResolvedTicketsCount(Long agentId) {
        return assignmentRepository.countResolvedTicketsByAgent(agentId);
    }
    
    public List<Ticket> getOpenTickets(Long agentId) {
        return assignmentRepository.findOpenTicketsByAgent(agentId);
    }
    
    public UserStatsDTO getUserStats(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        long totalTickets = assignmentRepository.countByAgentId(userId);
        long resolvedTickets = getResolvedTicketsCount(userId);
        long openTickets = getOpenTickets(userId).size();
    
        return new UserStatsDTO(
                totalTickets,
                resolvedTickets,
                openTickets
        );
    }
    
}
