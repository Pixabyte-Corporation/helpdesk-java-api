package com.pixabyte.helpdeskapi.projects.application.find;

import com.pixabyte.helpdeskapi.projects.domain.Project;
import com.pixabyte.helpdeskapi.projects.domain.ProjectRepository;
import com.pixabyte.helpdeskapi.shared.domain.Pagination;
import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;
import org.springframework.stereotype.Service;

@Service
public class FindAllProjectsUseCase {

    private final ProjectRepository projectRepository;

    public FindAllProjectsUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ResultsPage<Project> execute(Pagination pagination) {
        return projectRepository.findAll(pagination);
    }

}
