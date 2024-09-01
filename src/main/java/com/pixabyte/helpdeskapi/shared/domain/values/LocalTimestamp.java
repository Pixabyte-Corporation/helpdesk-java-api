package com.pixabyte.helpdeskapi.shared.domain.values;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class LocalTimestamp {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final LocalDateTime value;

    private LocalTimestamp(LocalDateTime value) {
        this.value = Objects.requireNonNull(value);
    }

    public static LocalTimestamp parse(String value) {
        try {
            LocalDateTime parsed = LocalDateTime.parse(value, FORMATTER);
            return new LocalTimestamp(parsed);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid LocalTimestampValue string: " + value, e);
        }
    }

    public LocalDateTime value() {
        return value;
    }

    public Long toEpochSecond() {
        return value.toEpochSecond(ZoneOffset.UTC);
    }

    public static LocalTimestamp ofEpochSecond(long epochSecond) {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.of("UTC"));
        return new LocalTimestamp(ldt);
    }

    public static LocalTimestamp now() {
        return new LocalTimestamp(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalTimestamp other = (LocalTimestamp) o;
        return value.equals(other.value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
