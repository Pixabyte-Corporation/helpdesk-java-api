package com.pixabyte.helpdeskapi.authentication.domain.repositories;

import com.pixabyte.helpdeskapi.authentication.domain.Organization;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository {
    Optional<Organization> findOrganizationById(UUID id);
}
