package com.deskify.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    private String uploadPath;

;

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)); 

        return userConverter.convertToDTO(user);
    }

    @Override
    public UserResponseDTO createUser(CreateUserDTO createUser) {
        if (userRepo.existsByEmail(createUser.getEmail())) {
            throw new UserAlreadyExists(createUser.getEmail());
        }

        User newUser = new User();
        newUser.setFirstName(createUser.getFirstName());
        newUser.setLastName(createUser.getLastName());
        newUser.setEmail(createUser.getEmail());
        newUser.setPassword(createUser.getPassword());

        User savedUser = userRepo.save(newUser);

        return userConverter.convertToDTO(savedUser);
    }

    @Transactional
    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return userList.stream()
                .map(userConverter::convertToDTO) 
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(Long id, UserResponseDTO userDTO) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (userRepo.existsByEmailAndIdNot(userDTO.getEmail(), user.getId())) {
            throw new UserAlreadyExists("Este email ya está siendo usado: " + userDTO.getEmail());
        }

        if (userRepo.existsByPhoneNumberAndIdNot(userDTO.getPhoneNumber(), user.getId())) {
            throw new UserAlreadyExists("Este número de teléfono ya está siendo usado: " + userDTO.getPhoneNumber());
        }

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

        return userConverter.convertToDTO(updatedUser);
    }

    @Override
    public UserResponseDTO uploadProfilePicture(Long userId, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("No se ha seleccionado ningún archivo para subir.");
            }

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if (user.getProfilePictureUrl() != null) {
                Path oldFile = Paths.get(uploadPath + user.getProfilePictureUrl());
                Files.deleteIfExists(oldFile);
            }

            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String uniqueName = UUID.randomUUID().toString();
            String fileName = userId + "_" + uniqueName + "." + extension;

            Path path = Paths.get(uploadPath + fileName);

            Files.createDirectories(path.getParent());

            Files.write(path, file.getBytes());

            user.setProfilePictureUrl(fileName);
            userRepo.save(user);

            return userConverter.convertToDTO(user);

        } catch (IOException e) {
            throw new RuntimeException("Error al subir el archivo: " + e.getMessage());
        }
    }

    public byte[] getProfilePicture(Long id) {
        Path imagePath = Paths.get(uploadPath + id + ".jpg"); 

        if (Files.exists(imagePath)) {
            try {
                return Files.readAllBytes(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Error reading profile picture", e);
            }
        } else {
            return null;
        }
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        List<User> userList = userRepo.findByRoleId((long) 4);
        return userList.stream()
                .map(userConverter::UserTodAgentDTO) 
                .collect(Collectors.toList());
    }

    public Long getResolvedTicketsCount(Long agentId) {
        return assignmentRepository.countResolvedTicketsByAgent(agentId);
    }

    public List<Ticket> getOpenTickets(Long agentId) {
        return assignmentRepository.findOpenTicketsByAgent(agentId);
    }

    @Override
    public UserStatsDTO getUserStats(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        long totalTickets = assignmentRepository.countByAgentId(userId);
        long resolvedTickets = getResolvedTicketsCount(userId);
        long openTickets = getOpenTickets(userId).size();

        return new UserStatsDTO(
                totalTickets,
                resolvedTickets,
                openTickets);
    }

}
