package com.masongarrett.taskmanagementsystem.controller;

import com.masongarrett.taskmanagementsystem.model.Tag;
import com.masongarrett.taskmanagementsystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/")
    public Tag createTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return tag;
    }

    @GetMapping("/")
    public List<Tag> getAllTags() {
        return tagService.get();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable long id) {
        Tag tag = tagService.get(id);
        if (tag == null) {
            throw new RuntimeException("Tag not found for ID: " + id);
        }
        return tag;
    }

    @PutMapping("/")
    public Tag updateTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return tag;
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable long id) {
        tagService.delete(id);
        return "Tag has been deleted with ID: " + id;
    }
}
