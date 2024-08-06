package com.pixabyte.helpdeskapi.projects.infrastructure.persistence;

import com.pixabyte.helpdeskapi.projects.domain.ProjectCounter;
import com.pixabyte.helpdeskapi.projects.domain.ProjectCounterRepository;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.infrastructure.mappers.ProjectCounterMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresProjectCounterRepository implements ProjectCounterRepository {

    private final JpaProjectCounterRepository jpaProjectCounterRepository;
    private final ProjectCounterMapper projectCounterMapper = Mappers.getMapper(ProjectCounterMapper.class);

    public PostgresProjectCounterRepository(JpaProjectCounterRepository jpaProjectCounterRepository) {
        this.jpaProjectCounterRepository = jpaProjectCounterRepository;
    }

    @Override
    public void save(ProjectCounter counter) {
        var entity = projectCounterMapper.toEntity(counter);
        jpaProjectCounterRepository.save(entity);
    }

    @Override
    public Optional<ProjectCounter> findById(ProjectId id) {
        return jpaProjectCounterRepository.findById(UUID.fromString(id.value()))
                .map(projectCounterMapper::projectCounterEntityToProjectCounter);
    }

}
