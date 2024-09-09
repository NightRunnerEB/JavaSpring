package com.example.JavaSpring.service;

import com.example.JavaSpring.model.User;
import com.example.JavaSpring.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void register(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(null, email, hashedPassword);
        userRepository.save(user);
    }

    public User authenticate(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new Exception("Invalid credentials");
        }
    }
}
