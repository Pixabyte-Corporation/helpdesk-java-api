package com.pixabyte.helpdeskapi.comments.infrastructure.controllers;

import com.pixabyte.helpdeskapi.comments.application.CommentRepresentation;
import com.pixabyte.helpdeskapi.comments.application.GetCommentsCommand;
import com.pixabyte.helpdeskapi.comments.application.GetCommentsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class GetCommentsGetController {

    private final GetCommentsUseCase getCommentsUseCase;

    public GetCommentsGetController(GetCommentsUseCase getCommentsUseCase) {
        this.getCommentsUseCase = getCommentsUseCase;
    }

    @GetMapping("/tickets/{ticketId}/comments")
    public ResponseEntity<Collection<CommentRepresentation>> getComments(@PathVariable("ticketId") String ticketId) {
        var command = new GetCommentsCommand(UUID.fromString(ticketId));
        var comments = getCommentsUseCase.execute(command);
        return ResponseEntity.ok(comments);
    }


}
