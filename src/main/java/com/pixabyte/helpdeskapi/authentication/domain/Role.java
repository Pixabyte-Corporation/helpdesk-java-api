package com.pixabyte.helpdeskapi.authentication.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class Role {
    private UUID id;
    private String name;
}
