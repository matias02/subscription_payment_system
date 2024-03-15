package com.example.microservicio_usuarios.controller;

import com.example.microservicio_usuarios.dto.UserRequest;
import com.example.microservicio_usuarios.dto.UserResponse;
import com.example.microservicio_usuarios.entity.Users;
import com.example.microservicio_usuarios.exception.UserNotFoundException;
import com.example.microservicio_usuarios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<Users> users = userService.findAllUsers();
        List<UserResponse> response = users.stream().map(user ->
                        new UserResponse(user.getId(), user.getUsername(), user.getEmail()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Users user = userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        UserResponse response = new UserResponse(user.getId(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        Users newUser = new Users(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail());
        Users savedUser = userService.saveUser(newUser);
        UserResponse response = new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        Users userDetails = new Users(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail());
        Users updatedUser = userService.updateUser(id, userDetails)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        UserResponse response = new UserResponse(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

