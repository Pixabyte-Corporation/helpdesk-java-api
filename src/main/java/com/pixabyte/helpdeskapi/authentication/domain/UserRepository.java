package com.pixabyte.helpdeskapi.authentication.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    Optional<User> findUserByEmail(String email);
    void save(User user);
}
