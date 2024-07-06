package com.pixabyte.helpdeskapi.authentication.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class User {
    private UUID id;
    private String email;
    private String password;
    private String name;
    private UUID organizationId;
    private UUID roleId;
}
