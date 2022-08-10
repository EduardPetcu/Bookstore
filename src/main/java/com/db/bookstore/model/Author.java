package com.db.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private int birthYear;

}
