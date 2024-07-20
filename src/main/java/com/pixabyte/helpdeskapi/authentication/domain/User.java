package com.pixabyte.helpdeskapi.authentication.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private LocalDateTime verifiedAt;

    private List<DomainEvent> events = new ArrayList<>();

    public static User createUser(UUID id, String email, String password, String name, UUID organizationId, UUID roleId) {
        var user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .organizationId(organizationId)
                .roleId(roleId)
                .verifiedAt(null)
                .build();
        var event = new UserRegisteredEvent(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getOrganizationId(),
                user.getRoleId()
        );
        if (Objects.isNull(user.events)) {
            user.events = new ArrayList<>();
        }
        user.events.add(event);
        return user;
    }

    public List<DomainEvent> pullEvents() {
        List<DomainEvent> pulledEvents = new ArrayList<>(events);
        events.clear();
        return pulledEvents;
    }

}
