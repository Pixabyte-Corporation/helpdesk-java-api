package com.pixabyte.helpdeskapi.comments.application;


import com.pixabyte.helpdeskapi.comments.domain.Comment;
import com.pixabyte.helpdeskapi.comments.domain.CommentRepository;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentUseCase {

    private final CommentRepository commentRepository;
    public final EventBus eventBus;

    public CreateCommentUseCase(CommentRepository commentRepository, EventBus eventBus) {
        this.commentRepository = commentRepository;
        this.eventBus = eventBus;
    }

    public void execute(CreateCommentCommand command) {
        Comment c = Comment.createComment(
                command.commentId(),
                command.content(),
                command.ticketId(),
                command.ownerId(),
                command.parentCommentId()
        );
        commentRepository.save(c);
        c.pullEvents().forEach(eventBus::publish);
    }

}
