package com.pixabyte.helpdeskapi.projects.application;

import com.pixabyte.helpdeskapi.projects.domain.Project;
import com.pixabyte.helpdeskapi.projects.domain.ProjectRepository;
import com.pixabyte.helpdeskapi.shared.domain.Pagination;
import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;

public class FindAllProjectsUseCase {

    private final ProjectRepository projectRepository;

    public FindAllProjectsUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ResultsPage<Project> execute(Pagination pagination) {
        return projectRepository.findAll(pagination);
    }

}
