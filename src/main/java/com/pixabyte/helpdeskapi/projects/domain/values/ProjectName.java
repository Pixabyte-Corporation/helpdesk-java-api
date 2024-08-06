package com.pixabyte.helpdeskapi.projects.domain.values;

import com.pixabyte.helpdeskapi.shared.domain.FieldValidationException;
import com.pixabyte.helpdeskapi.shared.domain.values.StringValue;

import java.util.Objects;

public class ProjectName extends StringValue {
    public ProjectName(String value) {
        super(value);
        ensureValidProjectName(value);
    }

    private void ensureValidProjectName(String value) {
        if (Objects.isNull(value)) {
            throw new FieldValidationException(
                    "Project name must have a value",
                    "name",
                    null
            );
        }
        if (value.length() < 2) {
            throw new FieldValidationException(
                    "Project name must have more than 2 characters",
                    "name",
                    value
            );
        }
    }

}
