package com.db.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String role;


}
