package com.deskify.service.interfaces;

import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;

public interface IUserService {

    public UserResponseDTO createUser(CreateUserDTO createUser);

}
