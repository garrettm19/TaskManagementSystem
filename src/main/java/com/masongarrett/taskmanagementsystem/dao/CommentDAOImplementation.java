package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Comment;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImplementation implements CommentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Comment> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Comment> query = currentSession.createQuery("from Comment", Comment.class);
        List<Comment> comments = query.getResultList();
        return comments;
    }

    @Override
    public Comment get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Comment comment = currentSession.get(Comment.class, id);
        return comment;
    }

    @Override
    public void save(Comment comment) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(comment);
    }

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Comment comment = currentSession.get(Comment.class, id);
        currentSession.remove(comment);
    }
}
