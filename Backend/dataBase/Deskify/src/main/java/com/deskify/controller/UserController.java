package com.deskify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deskify.dto.CreateUserDTO;
import com.deskify.dto.UserResponseDTO;
import com.deskify.repository.UserRepository;
import com.deskify.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

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

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserResponseDTO userDTO) {

        UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
        
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/update/{id}/profile-picture")
    public ResponseEntity<UserResponseDTO> uploadProfilePicture(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        UserResponseDTO updatedUser = userService.uploadProfilePicture(id, file);
        
        return ResponseEntity.ok(updatedUser);
    }

}
