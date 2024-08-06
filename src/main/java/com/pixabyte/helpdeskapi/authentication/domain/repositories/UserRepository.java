package com.pixabyte.helpdeskapi.authentication.domain.repositories;

import com.pixabyte.helpdeskapi.authentication.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);
    Optional<User> findUserByEmail(String email);
    void save(User user);
}
