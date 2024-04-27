package com.kadzys.VBooks;

import com.kadzys.VBooks.models.Role;
import com.kadzys.VBooks.models.User;

public class TestAuthenticationUtil {
    TestAuthenticationUtil() {}

    public static User getAdmin() {
        User admin = User.builder()
                .name("admin")
                .password("1234")
                .role(Role.ROLE_ADMIN)
                .build();
        return admin;
    }

}
