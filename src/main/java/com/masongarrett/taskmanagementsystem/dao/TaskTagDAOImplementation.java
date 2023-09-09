package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.TaskTag;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskTagDAOImplementation implements TaskTagDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<TaskTag> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<TaskTag> query = currentSession.createQuery("from TaskTag", TaskTag.class);
        List<TaskTag> taskTags = query.getResultList();
        return taskTags;
    }

    @Override
    public TaskTag get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        TaskTag taskTag = currentSession.get(TaskTag.class, id);
        return taskTag;
    }

    @Override
    public void save(TaskTag taskTag) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(taskTag);
    }

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        TaskTag taskTag = currentSession.get(TaskTag.class, id);
        currentSession.remove(taskTag);
    }
}
