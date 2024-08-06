package com.pixabyte.helpdeskapi.projects.infrastructure.persistence;

import com.pixabyte.helpdeskapi.projects.domain.Project;
import com.pixabyte.helpdeskapi.projects.domain.ProjectRepository;
import com.pixabyte.helpdeskapi.projects.domain.values.*;
import com.pixabyte.helpdeskapi.shared.domain.Pagination;
import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresProjectRepository implements ProjectRepository {

    private final JpaProjectRepository jpaProjectRepository;
    private final Logger logger = LoggerFactory.getLogger(PostgresProjectRepository.class);

    public PostgresProjectRepository(JpaProjectRepository jpaProjectRepository) {
        this.jpaProjectRepository = jpaProjectRepository;
    }
    @Observed(name = "save-PostgresProjectRepository")

    @Override
    public void save(Project project) {
        logger.info("PostgresProjectRepository#save - Saving entity with id={}",
                project.getId().value());
        jpaProjectRepository.save(toProjectEntity(project));
    }

    @Observed(name = "findAll-PostgresProjectRepository")
    @Override
    public ResultsPage<Project> findAll(Pagination pagination) {
        PageRequest pageRequest = PageRequest.of(
                pagination.pageNumber(),
                pagination.pageSize()
        );
        Page<ProjectEntity> results = jpaProjectRepository.findAll(pageRequest);
        var projectResults = results.map(this::toProject);
        return new ResultsPage<Project>(
                projectResults.getContent(),
                projectResults.getNumber(),
                projectResults.getSize(),
                projectResults.getTotalElements()
        );
    }
    @Observed(name = "findById-PostgresProjectRepository")

    @Override
    public Optional<Project> findById(ProjectId id) {
        return jpaProjectRepository.findById(UUID.fromString(id.value()))
                .map(this::toProject);
    }

    @Override
    public void refreshCounter(ProjectId id, TicketsCount ticketCounter) {

    }

    private ProjectEntity toProjectEntity(Project project) {
        return ProjectEntity.builder()
                .id(UUID.fromString(project.getId().value()))
                .name(project.getName().value())
                .description(project.getDescription().value())
                .organizationId(UUID.fromString(project.getOrganizationId().value()))
                .createdBy(UUID.fromString(project.getOwnerId().value()))
                .build();
    }

    private Project toProject(ProjectEntity entity) {
        return Project.recreate(
                new ProjectId(entity.getId().toString()),
                new ProjectName(entity.getName()),
                new ProjectDescription(entity.getDescription()),
                new OrganizationId(entity.getOrganizationId().toString()),
                new ProjectOwnerId(entity.getCreatedBy().toString())
        );
    }

}
