package com.kadzys.VBooks.services;

import com.kadzys.VBooks.controllers.auth.AuthenticationRequest;
import com.kadzys.VBooks.controllers.auth.AuthenticationResponse;
import com.kadzys.VBooks.controllers.auth.RegisterRequest;
import com.kadzys.VBooks.models.User;

import java.util.List;

public interface AuthenticationService {
    List<User> getUsers();

    boolean userExists(String username);

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
