package com.pixabyte.helpdeskapi.authentication.infrastructure;

import com.pixabyte.helpdeskapi.authentication.domain.User;
import com.pixabyte.helpdeskapi.authentication.domain.UserRepository;
import com.pixabyte.helpdeskapi.authentication.infrastructure.persistence.JpaUserRepository;
import com.pixabyte.helpdeskapi.authentication.infrastructure.persistence.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostgresUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public PostgresUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        var userEntityOpt = jpaUserRepository.findByEmail(email);
        if (userEntityOpt.isEmpty()) {
            return Optional.empty();
        }
        var entity = userEntityOpt.get();
        var user = User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .organizationId(entity.getOrganizationId())
                .roleId(entity.getRoleId())
                .build();
        return Optional.of(user);
    }

    @Override
    public void save(User user) {
        var entity = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .organizationId(user.getOrganizationId())
                .roleId(user.getRoleId())
                .build();
        jpaUserRepository.save(entity);
    }
}
