package com.example.security.security;

import com.example.security.model.Authority;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenProvider authenticationProvider;
    @Autowired
    UserRepository userRepository;


    //cho phép SpringSecurity sử dụng cơ chế xác thực ta cấu hình trong CustomAuthenProvider
    //AuthenticationManagerBuilder dùng để cấu hình thông tin đăng nhập,  có cung cấp 1 api
    //để thêm các Authetication Provider để xác thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests()
                .antMatchers("/book/read").hasRole(Authority.READ.toString())
                .antMatchers("/book/create").hasRole(Authority.CREATE.toString())
                .antMatchers("/book/delete").hasRole(Authority.DELETE.toString())
                .antMatchers("/book/edit").hasRole(Authority.EDIT.toString())
                .antMatchers("/book/search").hasRole(Authority.SEARCH.toString())
                .anyRequest().authenticated();
    }

    public static PasswordEncoder delegatePasswordEncoder(String encodingType) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingType, encoders);
    }

    @Bean
    public PasswordEncoder encoder() {
        return SecurityConfig.delegatePasswordEncoder("bcrypt");
    }

    
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Collection<UserDetails> users = new ArrayList<>();
        User userDetails = new User("t3",encoder().encode("123"));
        Role role = new Role();
        role.setName(Authority.SEARCH.toString());
        Role role1 = new Role();
        role.setName(Authority.EDIT.toString());
        userDetails.setRoles(Arrays.asList(role,role1));
        users.add(userDetails);
        return new InMemoryUserDetailsManager(users);
    }
}
