package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.CommentDAO;
import com.masongarrett.taskmanagementsystem.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Transactional
    @Override
    public List<Comment> get() {
        return commentDAO.get();
    }

    @Transactional
    @Override
    public Comment get(long id) {
        return commentDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Comment comment) {
        commentDAO.save(comment);
    }

    @Transactional
    @Override
    public void delete(long id) {
        commentDAO.delete(id);
    }
}
