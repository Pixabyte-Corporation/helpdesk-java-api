package com.pixabyte.helpdeskapi.comments.application.find;

import com.pixabyte.helpdeskapi.comments.domain.Comment;
import com.pixabyte.helpdeskapi.comments.domain.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetCommentsUseCase {


    private final CommentRepository repository;

    public GetCommentsUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public Collection<CommentRepresentation> execute(GetCommentsCommand command) {
        List<Comment> comments = repository.findAll(command.ticketId());

        Map<String, CommentRepresentation> parents = new HashMap<>();

        comments.stream()
                .filter(comment -> Objects.isNull(comment.getParentCommentId()))
                .forEach(comment -> {
                    var cr = CommentRepresentation.builder()
                        .id(comment.getId())
                        .ownerId(comment.getOwnerId())
                        .content(comment.getContent())
                        .answers(new ArrayList<>())
                        .build();
                    parents.put(comment.getId().toString(), cr);
        });

        comments.stream()
                .filter(comment -> Objects.nonNull(comment.getParentCommentId()))
                .forEach(comment -> {
                    var cr = CommentRepresentation.builder()
                            .id(comment.getId())
                            .ownerId(comment.getOwnerId())
                            .content(comment.getContent())
                            .answers(new ArrayList<>())
                            .build();
                    var parent = parents.get(comment.getParentCommentId().toString());
                    parent.getAnswers().add(cr);
                });

        return parents.values();

    }

}
