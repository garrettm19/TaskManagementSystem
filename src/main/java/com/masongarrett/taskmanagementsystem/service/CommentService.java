package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> get();
    Comment get(long id);
    void save(Comment comment);
    void delete(long id);
}
