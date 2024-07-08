package com.pixabyte.helpdeskapi.authentication.domain;

public interface TokenGenerator {
    String generate(User user);
}
