package com.example.microservicioautenticacion.service;

import com.example.microservicioautenticacion.dto.SignUpRequest;
import com.example.microservicioautenticacion.entity.ERole;
import com.example.microservicioautenticacion.entity.Role;
import com.example.microservicioautenticacion.entity.User;
import com.example.microservicioautenticacion.exception.AuthException;
import com.example.microservicioautenticacion.repository.RoleRepository;
import com.example.microservicioautenticacion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AuthException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AuthException("Email Address already in use!");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());

        String strRole = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        switch (strRole) {
            case "seller":
                Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(sellerRole);
                break;
            case "user":
            default:
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

}

