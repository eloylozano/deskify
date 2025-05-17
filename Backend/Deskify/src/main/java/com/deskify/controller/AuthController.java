package com.deskify.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deskify.dto.LoginRequestDTO;
import com.deskify.dto.LoginResponseDTO;
import com.deskify.dto.RegisterRequestDTO;
import com.deskify.model.Role;
import com.deskify.model.User;
import com.deskify.repository.RoleRepository;
import com.deskify.repository.UserRepository;
import com.deskify.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private JwtUtil jwtUtil;

    @Autowired
    public AuthController(JwtUtil jwtUtil,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "No autorizado"));
        }

        String email = authentication.getName(); // username/email desde el token ya autenticado

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Usuario no encontrado"));
        }

        User user = userOptional.get();

        Map<String, Object> userData = new HashMap<>();
        userData.put("id", user.getId());
        userData.put("firstName", user.getFirstName());
        userData.put("middleName", user.getMiddleName());
        userData.put("lastName", user.getLastName());
        userData.put("phoneNumber", user.getPhoneNumber());
        userData.put("email", user.getEmail());
        userData.put("company", user.getCompany());
        userData.put("profilePictureUrl", user.getProfilePictureUrl());
        userData.put("role", Map.of("id", user.getRole().getId(), "name", user.getRole().getName()));
        userData.put("createdAt", user.getCreatedAt());
        userData.put("updatedAt", user.getUpdatedAt());

        return ResponseEntity.ok(userData);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        try {
            if (request.getEmail() == null || request.getEmail().isEmpty() ||
                    request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Email y contraseña son requeridos"));
            }

            if (userRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body(new ErrorResponse("El email ya está registrado"));
            }

            // Obtener rol por defecto (USER)
            Optional<Role> roleOptional = roleRepository.findByName("USER");
            if (roleOptional.isEmpty()) {
                return ResponseEntity.internalServerError()
                        .body(new ErrorResponse("Error de configuración del sistema"));
            }

            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(roleOptional.get());

            userRepository.save(user);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorResponse("Error interno del servidor"));
        }
    }

    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmailExists(@RequestParam String email) {
        boolean exists = userRepository.existsByEmail(email);
        return ResponseEntity.ok().body(Collections.singletonMap("exists", exists));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Invalid Credentials"));
            }

            User user = userOptional.get();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Invalid Credentials"));
            }

            // Crear UserDetails para generar el token
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.emptyList());

            String token = jwtUtil.generateToken(userDetails);

            // Crear la respuesta con los datos del usuario y el token
            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(token);
            response.setUserId(user.getId());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setPhoneNumber(user.getPhoneNumber());
            response.setCompany(user.getCompany());
            response.setProfilePictureUrl(user.getProfilePictureUrl());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Error en el servidor"));
        }
    }

}