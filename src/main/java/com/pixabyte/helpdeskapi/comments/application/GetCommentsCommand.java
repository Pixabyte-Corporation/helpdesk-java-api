package com.pixabyte.helpdeskapi.comments.application;

import java.util.UUID;

public record GetCommentsCommand (
        UUID ticketId
) {
}
