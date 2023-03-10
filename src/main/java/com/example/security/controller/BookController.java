package com.example.security.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/book")
public class BookController {


    @GetMapping("/read")
    public String readBook(){
        return "read Book";
    }
    @GetMapping("/add")
    public String addBook(){
        return "add Book";
    }
}
