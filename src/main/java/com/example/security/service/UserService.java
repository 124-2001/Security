package com.example.security.service;

import com.example.security.model.Role;
import com.example.security.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);


}
