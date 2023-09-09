package com.masongarrett.taskmanagementsystem.dao;

import com.masongarrett.taskmanagementsystem.model.Tag;

import java.util.List;

public interface TagDAO {

    List<Tag> get();
    Tag get(long id);
    void save(Tag tag);
    void delete(long id);
}
