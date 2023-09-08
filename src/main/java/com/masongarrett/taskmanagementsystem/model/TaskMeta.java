package com.masongarrett.taskmanagementsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_meta")
public class TaskMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "taskId", nullable = false)
    private Long taskId;

    @Column(name = "key", length = 50, nullable = false)
    private String key;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TaskMeta{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", key='" + key + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

