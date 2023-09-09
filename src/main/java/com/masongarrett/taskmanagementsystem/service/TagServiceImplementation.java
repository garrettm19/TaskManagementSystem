package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.dao.TagDAO;
import com.masongarrett.taskmanagementsystem.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImplementation implements TagService {

    @Autowired
    private TagDAO tagDAO;

    @Transactional
    @Override
    public List<Tag> get() {
        return tagDAO.get();
    }

    @Transactional
    @Override
    public Tag get(long id) {
        return tagDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Tag tag) {
        tagDAO.save(tag);
    }

    @Transactional
    @Override
    public void delete(long id) {
        tagDAO.delete(id);
    }
}
