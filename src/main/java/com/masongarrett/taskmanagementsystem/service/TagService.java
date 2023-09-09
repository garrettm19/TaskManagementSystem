package com.masongarrett.taskmanagementsystem.service;

import com.masongarrett.taskmanagementsystem.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> get();
    Tag get(long id);
    void save(Tag tag);
    void delete(long id);
}
