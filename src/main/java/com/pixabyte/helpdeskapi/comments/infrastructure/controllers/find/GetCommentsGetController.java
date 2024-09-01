package com.pixabyte.helpdeskapi.comments.infrastructure.controllers.find;

import com.pixabyte.helpdeskapi.comments.application.find.CommentRepresentation;
import com.pixabyte.helpdeskapi.comments.application.find.GetCommentsCommand;
import com.pixabyte.helpdeskapi.comments.application.find.GetCommentsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class GetCommentsGetController {

    private final GetCommentsUseCase getCommentsUseCase;
    private final Logger logger = LoggerFactory.getLogger(GetCommentsUseCase.class);

    public GetCommentsGetController(GetCommentsUseCase getCommentsUseCase) {
        this.getCommentsUseCase = getCommentsUseCase;
    }

    @GetMapping("/tickets/{ticketId}/comments")
    public ResponseEntity<Collection<CommentRepresentation>> getComments(@PathVariable("ticketId") String ticketId) {
        logger.info("GetCommentsGetController#getComments - Starting getComments for ticket={}", ticketId);
        var command = new GetCommentsCommand(UUID.fromString(ticketId));
        var comments = getCommentsUseCase.execute(command);
        return ResponseEntity.ok(comments);
    }


}
