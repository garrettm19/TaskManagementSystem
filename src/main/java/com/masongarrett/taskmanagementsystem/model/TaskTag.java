package com.masongarrett.taskmanagementsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_tag")
public class TaskTag {
    @Id
    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    @Id
    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TaskTag{" +
                "task=" + task +
                ", tag=" + tag +
                '}';
    }
}