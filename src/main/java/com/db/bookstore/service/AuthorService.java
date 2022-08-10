package com.db.bookstore.service;

import com.db.bookstore.model.Author;
import com.db.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }
}
