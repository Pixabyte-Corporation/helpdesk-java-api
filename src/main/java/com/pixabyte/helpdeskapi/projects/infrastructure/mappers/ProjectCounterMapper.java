package com.pixabyte.helpdeskapi.projects.infrastructure.mappers;

import com.pixabyte.helpdeskapi.projects.domain.ProjectCounter;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.TicketId;
import com.pixabyte.helpdeskapi.projects.infrastructure.persistence.ProjectCounterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface ProjectCounterMapper {

    default ProjectCounter projectCounterEntityToProjectCounter(ProjectCounterEntity entity) {
        return ProjectCounter.recreate(
                mapId(entity.getProjectId()),
                mapTickets(entity.getTickets())
        );
    }

    @Mapping(
            target = "projectId", expression = "java(mapProjectId(pc.getId()))"
    )
    @Mapping(
            target = "tickets", expression = "java(mapTicketIdList(pc.getTickets()))"
    )
    ProjectCounterEntity toEntity(ProjectCounter pc);

    default Map<String, List<String>> mapTicketIdList(List<TicketId> tickets) {
        var map = new HashMap<String, List<String>>();
        map.put("tickets", tickets.stream().map(TicketId::value).collect(Collectors.toList()));
        return map;
    }

    default UUID mapProjectId(ProjectId id) {
        return UUID.fromString(id.toString());
    }

    default ProjectId mapId(UUID id) {
        return new ProjectId(id.toString());
    }

    default List<TicketId> mapTickets(Map<String, List<String>> hTickets) {
        return hTickets.get("tickets")
                .stream()
                .map(TicketId::new)
                .collect(Collectors.toList());
    }

}
