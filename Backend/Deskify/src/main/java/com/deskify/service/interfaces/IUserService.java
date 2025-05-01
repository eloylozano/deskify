package com.deskify.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.deskify.dto.AgentDTO;
import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.dto.UserStatsDTO;

public interface IUserService {

    public UserResponseDTO getUserById(Long id);

    public List<UserResponseDTO> getAllUsers();

    public UserResponseDTO createUser(CreateUserDTO createUser);

    public UserResponseDTO updateUser(Long id, UserResponseDTO userDTO);

    public UserResponseDTO uploadProfilePicture(Long userId, MultipartFile file);

    public List<AgentDTO> getAllAgents();

    public UserStatsDTO getUserStats(Long userId);

}
