package com.pixabyte.helpdeskapi.authentication.domain.repositories;

import com.pixabyte.helpdeskapi.authentication.domain.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {
    Optional<Role> findRoleById(UUID id);
}
