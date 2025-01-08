package com.deskify.service;

import java.io.File;
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

import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.dto.converter.UserConverter;
import com.deskify.error.UserAlreadyExists;
import com.deskify.error.UserNotFoundException;
import com.deskify.error.RoleNotFoundException;
import com.deskify.model.User;
import com.deskify.model.Role;
import com.deskify.repository.CommentRepository;
import com.deskify.repository.RoleRepository;
import com.deskify.repository.SubscriptionRepository;
import com.deskify.repository.UserRepository;
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
            // Verificar que el archivo no está vacío
            if (file.isEmpty()) {
                throw new RuntimeException("No se ha seleccionado ningún archivo para subir.");
            }

            // Crear directorio si no existe
            File dir = new File(profilePicturesPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Crear el nombre del archivo, evitando colisiones
            String fileName = userId + "_" + file.getOriginalFilename();

            // Ruta completa donde se guardará el archivo
            Path path = Paths.get(profilePicturesPath + fileName);

            // Guardar el archivo en la ruta definida
            Files.write(path, file.getBytes());

            // Recuperar el usuario y actualizar la ruta de la foto de perfil
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Actualizamos la URL del perfil (puedes almacenar solo el nombre de archivo si prefieres)
            user.setProfilePictureUrl(path.toString());

            // Guardamos el usuario actualizado
            userRepo.save(user);

            // Convertimos el usuario a DTO para devolver la respuesta
            return userConverter.convertToDTO(user);

        } catch (IOException e) {
            throw new RuntimeException("Error al subir el archivo: " + e.getMessage());
        }
    }

}
