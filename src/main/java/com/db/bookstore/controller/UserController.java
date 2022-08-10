package com.db.bookstore.controller;

import com.db.bookstore.model.Author;
import com.db.bookstore.model.Book;
import com.db.bookstore.model.User;

import com.db.bookstore.repository.AuthorRepository;
import com.db.bookstore.repository.BookRepository;
import com.db.bookstore.service.AuthorService;
import com.db.bookstore.service.BookService;
import com.db.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @GetMapping("/register")
    public ModelAndView getRegisterForm(){
        ModelAndView modelAndView = new ModelAndView("register-form");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView addUser(User user){
        user.setRole("client");
        userService.insertUser(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm(){
        ModelAndView modelAndView = new ModelAndView("login-form");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView verifyUser(User user, HttpServletResponse response){
        User user1;
        try {
            user1 = userService.findByUsernameOrEmailAndPassword(user);
            response.addCookie(new Cookie("id", "" + user1.getId()));
            response.addCookie(new Cookie("role", "" + user1.getRole()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
        //modelAndView.addObject(user1);
        //System.out.println(user1);
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashBoard(@CookieValue int id) {
        User user;
        List<Book> allBooks = new ArrayList<Book>();
        try {
            user = userService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("user",user);
        allBooks = bookService.getAllBooks();
        modelAndView.addObject("books", allBooks);
        return modelAndView;
    }
    @GetMapping("/add")
    public ModelAndView addBook(@CookieValue String role) {
        ModelAndView modelAndView;
        if (!role.equals("admin")) {
            modelAndView = new ModelAndView("acces-denied");
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("add-book");
            List<Author> authors = new ArrayList<>();
            authors = authorService.getAllAuthors();
            modelAndView.addObject("author",authors);
            Book book = new Book();
            modelAndView.addObject("book", book);
            return modelAndView;
        }
    }
    @PostMapping("/added")
    // adauga cartea, nu ataseaza lista de autori
    public ModelAndView addBookInDB(@ModelAttribute Book book) {
        ModelAndView modelAndView = new ModelAndView("added-book");
        bookService.insertBook(book);
        return modelAndView;
    }
    /*@GetMapping("/add/{title}")
    public ModelAndView addBook(@CookieValue String role, @PathVariable(value = "title")
                                                                           String title) {
        ModelAndView modelAndView;
        if (!role.equals("admin")) {
            modelAndView = new ModelAndView("acces-denied");
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("add-authors");
            List<Author> authors = new ArrayList<>();
            authors = authorService.getAllAuthors();
            try {
                Book book = bookService.findByTitle(title);
            } catch (Exception e) {
                e.printStackTrace();
            }
            modelAndView.addObject("author", authors);
            return modelAndView;
        }
    }*/
}
