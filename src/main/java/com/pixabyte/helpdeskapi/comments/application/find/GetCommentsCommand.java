package com.pixabyte.helpdeskapi.comments.application.find;

import java.util.UUID;

public record GetCommentsCommand (
        UUID ticketId
) {
}
