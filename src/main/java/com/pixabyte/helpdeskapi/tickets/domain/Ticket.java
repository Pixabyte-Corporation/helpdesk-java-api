package com.pixabyte.helpdeskapi.tickets.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class Ticket {
    private UUID id;
    private String title;
    private String description;
    private Integer priority;
    private String status;
    private UUID reporterId;
    private UUID assignedToId;
    private UUID projectId;

}
