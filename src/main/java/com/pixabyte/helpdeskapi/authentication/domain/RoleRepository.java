package com.pixabyte.helpdeskapi.authentication.domain;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {
    Optional<Role> findRoleById(UUID id);
}
