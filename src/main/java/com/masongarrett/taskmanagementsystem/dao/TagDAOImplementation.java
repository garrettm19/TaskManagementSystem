package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Tag;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAOImplementation implements TagDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Tag> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Tag> query = currentSession.createQuery("from Tag", Tag.class);
        List<Tag> tags = query.getResultList();
        return tags;
    }

    @Override
    public Tag get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Tag tag = currentSession.get(Tag.class, id);
        return tag;
    }

    @Override
    public void save(Tag tag) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(tag);
    }

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Tag tag = currentSession.get(Tag.class, id);
        currentSession.remove(tag);
    }
}
