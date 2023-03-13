package com.example.security.controller;

import com.example.security.model.Book;
import com.example.security.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @GetMapping("/read")
    public String readBook() {
        return "read Book";
    }

    @GetMapping("/search")
    public String getList(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "search.html";
    }

    @GetMapping("/create")
    public String createBook() {
        return "add Book";
    }

    @GetMapping("/delete")
    public String deleteBook() {
        return "delete Book";
    }
    @GetMapping("/edit")
    public String editBook(){

        return "edit Book";
    }

}
