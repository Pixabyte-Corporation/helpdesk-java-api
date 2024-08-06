package com.pixabyte.helpdeskapi.projects.infrastructure.persistence;


import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "project_counter")
public class ProjectCounterEntity {
    @Id
    private UUID projectId;
    @Type(JsonType.class)
    private Map<String, List<String>> tickets;

}
