package com.masongarrett.taskmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "title", length = 512, nullable = false)
    private String title;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "tag", length = 75)
    private String tag;

    public Task(Long userId, String title, String description, String state, String tag) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.state = state;
        this.tag = tag;
        this.createdAt = new java.util.Date(System.currentTimeMillis());
    }

}