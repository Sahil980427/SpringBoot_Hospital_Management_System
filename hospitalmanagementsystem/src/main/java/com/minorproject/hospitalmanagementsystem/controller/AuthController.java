package com.minorproject.hospitalmanagementsystem.controller;


import com.minorproject.hospitalmanagementsystem.config.JwtUtil;
import com.minorproject.hospitalmanagementsystem.dto.UserDTO;
import com.minorproject.hospitalmanagementsystem.entity.*;
import com.minorproject.hospitalmanagementsystem.repo.AdminRepository;
import com.minorproject.hospitalmanagementsystem.repo.DoctorRepository;
import com.minorproject.hospitalmanagementsystem.repo.PatientRepository;
import com.minorproject.hospitalmanagementsystem.service.UserService; // Ensure you have a UserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService; // Service to handle user operations
    @Autowired
    private AuthenticationManager authenticationManager; // Inject AuthenticationManager

    @Autowired
    private JwtUtil jwtUtil; // Inject JwtUtil
    @Autowired
    private PasswordEncoder passwordEncoder; // For encoding passwords
    @PostMapping("/login")
    public ResponseEntity<String> loginUser (@RequestBody UserDTO userDTO) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );

            // Generate JWT token
            String token = jwtUtil.generateToken(userDTO.getEmail());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful. Discard the token on the client side.");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
    // Check if the email already exists
    if (userService.findByEmail(userDTO.getEmail()) != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Email already exists
    }

    // Create a new User entity from the DTO
    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode password
    user.setFullName(userDTO.getFullName());
    user.setRole(userDTO.getRole());

    // Save in user repository
    User savedUser = userService.saveUser(user);

    // Save in appropriate role-specific repository
    switch (user.getRole()) {
        case DOCTOR -> {
            Doctor doctor = new Doctor();
            doctor.setEmail(user.getEmail());
            doctor.setPassword(user.getPassword());
            doctor.setFullName(user.getFullName());
            doctor.setPhoneNumber(userDTO.getPhoneNumber());
            doctor.setSpecialization(userDTO.getSpecialization());
            doctor.setRole(Role.DOCTOR);
            doctorRepository.save(doctor);
        }
        case PATIENT -> {
            Patient patient = new Patient();
            patient.setEmail(user.getEmail());
            patient.setPassword(user.getPassword());
            patient.setFullName(user.getFullName());
            patient.setPhoneNumber(userDTO.getPhoneNumber());
            patient.setRole(Role.PATIENT);
            patientRepository.save(patient);
        }
        case ADMIN -> {
            Admin admin = new Admin();
            admin.setEmail(user.getEmail());
            admin.setPassword(user.getPassword());
            admin.setFullName(user.getFullName());
            admin.setRole(Role.ADMIN);
            adminRepository.save(admin);
        }
    }

    UserDTO responseDTO = new UserDTO(savedUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
}

}