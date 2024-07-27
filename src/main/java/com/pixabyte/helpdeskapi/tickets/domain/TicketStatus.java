package com.pixabyte.helpdeskapi.tickets.domain;

public enum TicketStatus {
    OPEN("OPEN"),
    IN_PROGRESS("IN_PROGRESS"),
    RESOLVED("RESOLVED"),
    CLOSED("CLOSED"),
    BLOCKED("BLOCKED");

    private final String value;

    TicketStatus(String value) {
        this.value = value;
    }

    public static TicketStatus of(String value) {
        for (TicketStatus status: TicketStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ticket status: " + value);
    }
    public String getValue() {
        return value;
    }

}
