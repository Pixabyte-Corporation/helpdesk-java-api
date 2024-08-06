package com.pixabyte.helpdeskapi.projects.domain.values;

import com.pixabyte.helpdeskapi.shared.domain.FieldValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNameTest {

    @Test
    void shouldTrowsIllegalArgumentExceptionWhenProjectNameIsEmpty() {
        assertThrows(FieldValidationException.class, () -> {
            new ProjectName("");
        });
    }
}