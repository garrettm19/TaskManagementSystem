package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Activity;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAOImplementation implements ActivityDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Activity> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Activity> query = currentSession.createQuery("from Activity", Activity.class);
        List<Activity> activities = query.getResultList();
        return activities;
    }

    @Override
    public Activity get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Activity activity = currentSession.get(Activity.class, id);
        return activity;
    }

    @Override
    public void save(Activity activity) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(activity);
    }

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Activity activity = currentSession.get(Activity.class, id);
        currentSession.remove(activity);
    }
}
