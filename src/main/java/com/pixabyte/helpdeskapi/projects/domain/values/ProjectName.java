package com.pixabyte.helpdeskapi.projects.domain.values;

import com.pixabyte.helpdeskapi.shared.domain.values.StringValue;

public class ProjectName extends StringValue {
    public ProjectName(String value) {
        super(value);
        ensureValidProjectName(value);
    }

    private void ensureValidProjectName(String value) {
        if (value.length() < 2) {
            throw new IllegalArgumentException("Project name must have more than 2 characters");
        }
    }

}
