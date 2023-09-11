package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //@Repository marks the class as a database repository or DAO (data access object) which will interact with the database
public class UserDAOImplementation implements UserDAO {

    @Autowired
    private EntityManager entityManager; //EntityManager is a tool  JPA (Java Persistence API) used for making interactions with our database easy

    @Override
    public List<User> get() { //Session is a tool from Hibernate that opens for each query to act as a buffer and improve efficiency between the application and database
        Session currentSession = entityManager.unwrap(Session.class); //We unwrap Session.class because this is our way of transitioning from using the standard JPA interface to Hibernates implementation
        Query<User> query = currentSession.createQuery("from User", User.class); //Query using Hibernate and it's Hibernate Query Language (HQL)
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    public User get(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User userObj = currentSession.get(User.class, id);
        return userObj;
    }

    @Override
    public User get(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        User userObj = query.uniqueResult();

        return userObj;
    }


    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(user);
    }
    /*
    merge() is like saying, "Hibernate, I have this object, and I'm not sure if it's new or existing.
    If you already know about it, please update it with the changes I made. If not, start tracking it and save it later."

    persist() is like saying, "Hey, Hibernate, I have this brand-new object, and I want you to keep track of it and eventually save
    it to the database when it's appropriate. Don't worry about any existing objects with the same identifier."

    saveOrUpdate() and save() methods are deprecated
     */

    @Override
    public void delete(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User userObj = currentSession.get(User.class, id);
        currentSession.remove(userObj);
    }

    @Override
    public User findByEmailPassword(String email, String password) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User where email = :email and password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User userObj = query.uniqueResult();

        return userObj;
    }

}
