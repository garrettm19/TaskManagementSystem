package com.masongarrett.taskmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //@Entity is used to tell Java that this class represents a database table
@Table(name = "user") //@Table is used to define the specific table
public class User {

    @Id //@Id marks the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue(strategy = GenerationType.IDENTITY) is used to automatically generate values for identification
    @Column(name = "user_id") //@Column defines the specific column
    private Long id;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "mobile", length = 15, unique = true)
    private String mobile;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "password", length = 32, nullable = false)
    private String password;

    @Column(name = "registeredAt", nullable = false)
    private Date registeredAt;

    @Column(name = "profile", columnDefinition = "TEXT")
    private String profile;
}
