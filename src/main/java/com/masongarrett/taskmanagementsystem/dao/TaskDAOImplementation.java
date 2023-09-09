package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Task;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImplementation implements TaskDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Task> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("from Task", Task.class);
        List<Task> tasks = query.getResultList();
        return tasks;
    }

    @Override
    public Task get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Task task = currentSession.get(Task.class, id);
        return task;
    }

    @Override
    public void save(Task task) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(task);
    }

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Task task = currentSession.get(Task.class, id);
        currentSession.remove(task);
    }
}
