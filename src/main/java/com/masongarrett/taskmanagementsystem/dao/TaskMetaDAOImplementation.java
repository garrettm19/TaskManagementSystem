package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.TaskMeta;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskMetaDAOImplementation implements TaskMetaDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TaskMeta> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<TaskMeta> query = currentSession.createQuery("from TaskMeta", TaskMeta.class);
        List<TaskMeta> taskMetas = query.getResultList();
        return taskMetas;
    }

    @Override
    public TaskMeta get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        TaskMeta taskMeta = currentSession.get(TaskMeta.class, id);
        return taskMeta;
    }

    @Override
    public void save(TaskMeta taskMeta) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(taskMeta);
    }

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        TaskMeta taskMeta = currentSession.get(TaskMeta.class, id);
        currentSession.remove(taskMeta);
    }
}
