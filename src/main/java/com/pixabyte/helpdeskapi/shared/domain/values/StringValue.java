package com.pixabyte.helpdeskapi.shared.domain.values;

public abstract class StringValue {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public String value() {
        return value;
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
        StringValue other = (StringValue) o;
        return value.equals(other.value());
    }

}
