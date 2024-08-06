package com.pixabyte.helpdeskapi.authentication.infrastructure;

import com.pixabyte.helpdeskapi.authentication.domain.Role;
import com.pixabyte.helpdeskapi.authentication.domain.repositories.RoleRepository;
import com.pixabyte.helpdeskapi.authentication.infrastructure.persistence.JpaRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresRoleRepository implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public PostgresRoleRepository(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findRoleById(UUID id) {
        var roleOpt = jpaRoleRepository.findById(id);
        if (roleOpt.isEmpty()) {
            return Optional.empty();
        }
        var roleEntity = roleOpt.get();
        var role = Role.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
        return Optional.of(role);
    }
}
