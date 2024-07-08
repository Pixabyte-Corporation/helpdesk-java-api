package com.pixabyte.helpdeskapi.authentication.domain;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String rawPassword, String hashedPassword);
}
