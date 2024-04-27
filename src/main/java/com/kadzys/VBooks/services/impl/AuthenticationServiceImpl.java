package com.kadzys.VBooks.services.impl;

import com.kadzys.VBooks.config.JwtService;
import com.kadzys.VBooks.controllers.auth.AuthenticationRequest;
import com.kadzys.VBooks.controllers.auth.AuthenticationResponse;
import com.kadzys.VBooks.controllers.auth.RegisterRequest;
import com.kadzys.VBooks.models.User;
import com.kadzys.VBooks.repositories.UserRepository;
import com.kadzys.VBooks.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public List<User> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsUserByName(username);
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (request.getName() == null || request.getPassword() == null) {
            throw new RuntimeException("name and password are required" + request.getName() + request.getPassword());
        }
        var user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        return getAuthenticationResponse(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword())
        );
        User user = userRepository.findByName(request.getName())
                .orElseThrow();

        return  getAuthenticationResponse(user);
    }

    private AuthenticationResponse getAuthenticationResponse(User user) {
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .username(user.getName())
                .build();
    }
}
