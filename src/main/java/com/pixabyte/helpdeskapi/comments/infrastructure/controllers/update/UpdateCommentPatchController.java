package com.pixabyte.helpdeskapi.comments.infrastructure.controllers.update;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import com.pixabyte.helpdeskapi.comments.application.create.CreateCommentCommand;
import com.pixabyte.helpdeskapi.comments.application.create.CreateCommentUseCase;
import com.pixabyte.helpdeskapi.comments.application.update.UpdateCommentCommand;
import com.pixabyte.helpdeskapi.comments.application.update.UpdateCommentUseCase;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentContent;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentId;
import com.pixabyte.helpdeskapi.comments.domain.values.TicketId;
import com.pixabyte.helpdeskapi.shared.domain.values.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class UpdateCommentPatchController {

    private final UpdateCommentUseCase updateCommentUseCase;
    private final Logger logger = LoggerFactory.getLogger(UpdateCommentPatchController.class);

    public UpdateCommentPatchController(UpdateCommentUseCase updateCommentUseCase) {
        this.updateCommentUseCase = updateCommentUseCase;
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> createComment(@PathVariable("commentId") String commentId,
                                              @RequestBody UpdateCommentRequest request) {
        logger.info("UpdateCommentPatchController#createComment - Updating comment with id={}", commentId);
        var command = new UpdateCommentCommand(
                new CommentId(commentId),
                new CommentContent(request.content())
        );
        updateCommentUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
