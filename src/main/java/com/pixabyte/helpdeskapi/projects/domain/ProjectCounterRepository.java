package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;

import java.util.Optional;

public interface ProjectCounterRepository {

    void save(ProjectCounter counter);
    Optional<ProjectCounter> findById(ProjectId id);

}
