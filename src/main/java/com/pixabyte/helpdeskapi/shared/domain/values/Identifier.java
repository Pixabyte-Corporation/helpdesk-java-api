package com.pixabyte.helpdeskapi.shared.domain.values;

import java.io.Serializable;
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
        UUID.fromString(value);
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
