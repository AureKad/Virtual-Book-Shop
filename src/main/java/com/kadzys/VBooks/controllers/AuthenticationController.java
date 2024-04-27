package com.kadzys.VBooks.controllers;

import com.kadzys.VBooks.controllers.auth.AuthenticationRequest;
import com.kadzys.VBooks.controllers.auth.AuthenticationResponse;
import com.kadzys.VBooks.controllers.auth.RegisterRequest;
import com.kadzys.VBooks.models.Role;
import com.kadzys.VBooks.models.User;
import com.kadzys.VBooks.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @PostMapping("/register/user")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegisterRequest request
    ) {
        if (service.userExists(request.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        request.setRole(Role.ROLE_USER);
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ){
        if (service.userExists(request.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        request.setRole(Role.ROLE_ADMIN);
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }
}
