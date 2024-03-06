package com.example.microservicioautenticacion.controller;

import com.example.microservicioautenticacion.dto.JwtAuthenticationResponse;
import com.example.microservicioautenticacion.dto.LoginRequest;
import com.example.microservicioautenticacion.dto.SignUpRequest;
import com.example.microservicioautenticacion.exception.AuthException;
import com.example.microservicioautenticacion.security.JwtTokenProvider;
import com.example.microservicioautenticacion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            userService.registerUser(signUpRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthException("Error during registration: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthException("Invalid username/password supplied"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok().build();
    }



}
