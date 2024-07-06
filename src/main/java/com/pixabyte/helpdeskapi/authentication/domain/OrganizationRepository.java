package com.pixabyte.helpdeskapi.authentication.domain;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository {
    Optional<Organization> findOrganizationById(UUID id);
}
