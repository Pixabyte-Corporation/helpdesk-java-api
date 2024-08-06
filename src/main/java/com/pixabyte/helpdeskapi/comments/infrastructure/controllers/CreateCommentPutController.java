package com.pixabyte.helpdeskapi.comments.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import com.pixabyte.helpdeskapi.comments.application.create.CreateCommentCommand;
import com.pixabyte.helpdeskapi.comments.application.create.CreateCommentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CreateCommentPutController {

    private final CreateCommentUseCase createCommentUseCase;

    public CreateCommentPutController(CreateCommentUseCase createCommentUseCase) {
        this.createCommentUseCase = createCommentUseCase;
    }

    @PutMapping("/tickets/{ticketId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable String ticketId,
                                              @RequestBody CreateCommentPutRequest request,
                                              Authentication authentication) {
        HelpDeskUserDetails helpDeskUserDetails = (HelpDeskUserDetails) authentication.getPrincipal();
        var command = new CreateCommentCommand(
                request.commentId(),
                helpDeskUserDetails.getId(),
                request.content(),
                UUID.fromString(ticketId),
                request.parentCommentId());
        createCommentUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
