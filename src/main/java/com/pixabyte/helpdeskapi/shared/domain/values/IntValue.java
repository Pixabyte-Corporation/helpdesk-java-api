package com.pixabyte.helpdeskapi.shared.domain.values;

import java.util.Objects;

public abstract class IntValue {

    private Integer value;

    public IntValue(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntValue intValue = (IntValue) o;
        return Objects.equals(value, intValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
