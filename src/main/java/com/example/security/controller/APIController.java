package com.example.security.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.security.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("/api")
public class APIController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        result.add(new Product("Coffe Machine", 150));
        result.add(new Product("Apple Watch", 250));
        result.add(new Product("Eink Book Reader", 350));
        return result;
    }

    @GetMapping("/user")
    public String getUser(){
        return "Hello user ";
    }

    @GetMapping("/operator")
    public String getOperator(){
        return "Get operator";
    }

    @GetMapping("/error")
    public String getError(){
        return "error";
    }
}
