package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired

    @GetMapping("/read")
    public String readBook() {
        return "read Book";
    }

    @GetMapping("/add")
    public String addBook() {
        return "add Book";
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
