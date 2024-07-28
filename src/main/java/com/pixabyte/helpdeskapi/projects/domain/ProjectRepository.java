package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.TicketsCount;
import com.pixabyte.helpdeskapi.shared.domain.Pagination;
import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;

import java.util.Optional;

public interface ProjectRepository {

    void save(Project project);
    ResultsPage<Project> findAll(Pagination pagination);
    Optional<Project> findById(ProjectId id);
    void refreshCounter(ProjectId id, TicketsCount ticketCounter);

}
