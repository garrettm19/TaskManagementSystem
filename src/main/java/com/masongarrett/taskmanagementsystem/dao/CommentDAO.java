package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Comment;

import java.util.List;

public interface CommentDAO {

    List<Comment> get();
    Comment get(long id);
    void save(Comment comment);
    void delete(long id);
}
