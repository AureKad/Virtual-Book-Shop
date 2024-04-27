package com.kadzys.VBooks.controllers.auth;

import com.kadzys.VBooks.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String password;
    private Role role;

}
