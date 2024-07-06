package com.pixabyte.helpdeskapi.authentication.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByEmail(String email);
    void save(User user);
}
