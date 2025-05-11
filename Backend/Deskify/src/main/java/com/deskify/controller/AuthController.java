package com.deskify.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deskify.dto.LoginRequestDTO;
import com.deskify.dto.LoginResponseDTO;
import com.deskify.dto.RegisterRequestDTO;
import com.deskify.dto.ResponseMessage;
import com.deskify.model.Role;
import com.deskify.model.User;
import com.deskify.repository.RoleRepository;
import com.deskify.repository.UserRepository;
import com.deskify.security.CustomUserDetailsService;
import com.deskify.security.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        // Verificar si el usuario existe
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Correo electrónico no registrado");
        }
    
        User user = userOptional.get();
    
        // Verificar si la contraseña es correcta
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Contraseña incorrecta");
        }
    
        // Generar el token JWTa
        String token = jwtUtil.generateToken(user); // Suponiendo que tienes una clase para generar el JWT
        
        // Incluir el id del usuario en la respuesta
        LoginResponseDTO response = new LoginResponseDTO(token, user.getId());
        
        return ResponseEntity.ok(response); // Devolver el token y el id en la respuesta
    }
    

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email ya registrado");
        }
        
        Optional<Role> roleOptional = roleRepository.findByName("User");
        if (roleOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Rol 'User' no encontrado en la base de datos");
        }
    
        Role role = roleOptional.get();
    
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());  
        
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(role);
        
        userRepository.save(user);
        
        return ResponseEntity.ok(new ResponseMessage("Usuario registrado con éxito"));

    }
    

}
