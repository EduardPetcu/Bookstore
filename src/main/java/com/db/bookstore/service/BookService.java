package com.db.bookstore.service;

import com.db.bookstore.model.Book;
import com.db.bookstore.model.User;
import com.db.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void insertBook(Book book) {
        bookRepository.save(book);
    }
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }
    public Book findByTitle(String title) throws Exception {
        List<Book> books = bookRepository.findByTitle(title);
        if(books.size() == 0) {
            throw new Exception("No books found");
        }
        if(books.size() == 1) {
            return books.get(0);
        }
        throw new Exception("Database error");
    }
}
