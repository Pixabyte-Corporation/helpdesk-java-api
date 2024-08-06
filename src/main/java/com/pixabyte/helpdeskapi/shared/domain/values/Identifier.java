package com.pixabyte.helpdeskapi.shared.domain.values;

import com.pixabyte.helpdeskapi.shared.domain.FieldValidationException;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Identifier implements Serializable {

    final protected String value;

    public Identifier(String value) {
        ensureValidIdentifier(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    private void ensureValidIdentifier(String value) throws IllegalArgumentException {
        if (Objects.isNull(value)) {
            throw new FieldValidationException(
                    "Identifier must have a value",
                    "id",
                    null
            );
        }
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new FieldValidationException(
                    "Identifier is not valid",
                    "id",
                    value
            );
        }

    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identifier other = (Identifier) o;
        return value.equals(other.value());
    }

}
