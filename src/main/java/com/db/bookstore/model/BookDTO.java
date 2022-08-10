package com.db.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {

    private String title;
    private Set<String> authorList;
    private int pages;
    private String publisher;
}
