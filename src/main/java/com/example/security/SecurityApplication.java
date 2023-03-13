package com.example.security;

import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
//        Role role = new Role();
//        role.setName(Authority.CREATE);
//        Role role1 = new Role();
//        role1.setName(Authority.DELETE);
//        Role role2 = new Role();
//        role2.setName(Authority.EDIT);
//        Role role3 = new Role();
//        role3.setName(Authority.READ);
//        Role role4 = new Role();
//        role4.setName(Authority.SEARCH);
//
//        roleRepository.saveAll(Arrays.asList(role,role1,role3,role2,role4));
//
//        User user = new User();
//        user.setUsername("n");
//        user.setPassword("1234");
//        userRepository.save(user);
    }
}
