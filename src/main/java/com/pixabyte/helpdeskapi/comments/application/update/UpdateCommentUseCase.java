package com.pixabyte.helpdeskapi.comments.application.update;

import com.pixabyte.helpdeskapi.comments.domain.Comment;
import com.pixabyte.helpdeskapi.comments.domain.CommentNotFound;
import com.pixabyte.helpdeskapi.comments.domain.CommentRepository;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCommentUseCase {

    private final CommentRepository commentRepository;
    private final EventBus eventBus;


    public UpdateCommentUseCase(CommentRepository commentRepository, EventBus eventBus) {
        this.commentRepository = commentRepository;
        this.eventBus = eventBus;
    }

    public void execute(UpdateCommentCommand command) {
        Optional<Comment> optComment = commentRepository.findById(command.commentId());
        if (optComment.isEmpty()) {
            throw new CommentNotFound();
        }
        Comment c = optComment.get();
        c.update(command.content());
        commentRepository.save(c);
        c.pullEvents().forEach(eventBus::publish);
    }


}
