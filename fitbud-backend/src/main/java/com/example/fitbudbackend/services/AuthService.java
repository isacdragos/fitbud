package com.example.fitbudbackend.services;

import com.example.fitbudbackend.dtos.LoginRequest;
import com.example.fitbudbackend.dtos.LoginResponse;
import com.example.fitbudbackend.dtos.RegisterRequest;
import com.example.fitbudbackend.entities.User;
import com.example.fitbudbackend.exceptions.EmailAlreadyExistsException;
import com.example.fitbudbackend.exceptions.InvalidCredentialsException;
import com.example.fitbudbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new InvalidCredentialsException();
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }
}
