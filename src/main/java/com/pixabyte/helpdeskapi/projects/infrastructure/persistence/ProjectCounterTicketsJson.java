package com.pixabyte.helpdeskapi.projects.infrastructure.persistence;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectCounterTicketsJson {

    private List<String> tickets = new ArrayList<>();

    public ProjectCounterTicketsJson(List<String> tickets) {
        this.tickets = tickets;
    }
}
