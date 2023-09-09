package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.Comment;
import com.masongarrett.taskmanagementsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    public Comment createComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return comment;
    }

    @GetMapping("/")
    public List<Comment> getAllComments() {
        return commentService.get();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable long id) {
        Comment comment = commentService.get(id);
        if (comment == null) {
            throw new RuntimeException("Comment not found for ID: " + id);
        }
        return comment;
    }

    @PutMapping("/")
    public Comment updateComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return comment;
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable long id) {
        commentService.delete(id);
        return "Comment has been deleted with ID: " + id;
    }
}
