package com.deskify.service.interfaces;

import java.util.List;

import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;

public interface IUserService {

    public List<UserResponseDTO> getAllUsers();

    public UserResponseDTO createUser(CreateUserDTO createUser);

    public UserResponseDTO updateUser(Long id, UserResponseDTO userDTO);

}
