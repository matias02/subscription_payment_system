package com.example.microservicio_usuarios.service;

import com.example.microservicio_usuarios.entity.Users;
import com.example.microservicio_usuarios.exception.UserNotFoundException;
import com.example.microservicio_usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Users saveUser(Users Users) {
        return userRepository.save(Users);
    }

    public Optional<Users> updateUser(Long id, Users userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        });
    }

    public Optional<Void> deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return null;
        });
    }
}
