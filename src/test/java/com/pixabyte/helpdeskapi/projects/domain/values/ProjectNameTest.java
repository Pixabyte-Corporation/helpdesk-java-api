package com.pixabyte.helpdeskapi.projects.domain.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNameTest {

    @Test
    void shouldTrowsIllegalArgumentExceptionWhenProjectNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectName("");
        });
    }
}