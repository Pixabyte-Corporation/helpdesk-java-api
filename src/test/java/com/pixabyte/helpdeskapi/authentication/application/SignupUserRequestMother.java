package com.pixabyte.helpdeskapi.authentication.application;

import java.util.UUID;

public class SignupUserRequestMother {

    public static SignupUserRequest randomRequest() {
        UUID organizationId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String email = "pepe@gmail.com";
        String password = "awesomepass";
        String name = "Pepe";
        return new SignupUserRequest(
                organizationId,
                roleId,
                userId,
                email,
                password,
                name
        );
    }

}
