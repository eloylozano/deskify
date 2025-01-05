package com.deskify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        List<UserResponseDTO> lista = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO userDTO) {
        UserResponseDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
