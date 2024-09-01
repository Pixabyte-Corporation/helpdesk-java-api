package com.pixabyte.helpdeskapi.comments.infrastructure.controllers.create;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import com.pixabyte.helpdeskapi.comments.application.create.CreateCommentCommand;
import com.pixabyte.helpdeskapi.comments.application.create.CreateCommentUseCase;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentContent;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentId;
import com.pixabyte.helpdeskapi.comments.domain.values.TicketId;
import com.pixabyte.helpdeskapi.shared.domain.values.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
                new CommentId(request.commentId()),
                new UserId(helpDeskUserDetails.getId().toString()),
                new CommentContent(request.content()),
                new TicketId(ticketId),
                Objects.isNull(request.parentCommentId())? null: new CommentId(request.parentCommentId()));
        createCommentUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
