package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//    @Autowired
//private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @PostMapping("/register")
//    public String registerNewUser(@RequestParam("username") String username, @RequestParam("password") String password) {
//        String encodedPassword = bCryptPasswordEncoder.encode(password);
//
//    }
}
