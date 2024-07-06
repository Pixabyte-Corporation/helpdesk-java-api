package com.pixabyte.helpdeskapi.authentication.infrastructure;

import com.pixabyte.helpdeskapi.authentication.domain.Organization;
import com.pixabyte.helpdeskapi.authentication.domain.OrganizationRepository;
import com.pixabyte.helpdeskapi.authentication.infrastructure.persistence.JpaOrganizationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresOrganizationRepository implements OrganizationRepository {

    private final JpaOrganizationRepository jpaOrganizationRepository;

    public PostgresOrganizationRepository(JpaOrganizationRepository jpaOrganizationRepository) {
        this.jpaOrganizationRepository = jpaOrganizationRepository;
    }
    @Override
    public Optional<Organization> findOrganizationById(UUID id) {
        var orgOpt = jpaOrganizationRepository.findById(id);
        if (orgOpt.isEmpty()) {
            return Optional.empty();
        }
        var orgEntity = orgOpt.get();
        var organization = Organization.builder()
                .id(orgEntity.getId())
                .name(orgEntity.getName())
                .build();
        return Optional.of(organization);
    }
}
