package com.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.WeakHashMap;

public class Config3 extends WebSecurityConfigurerAdapter {
    // chèn 1 bean
    //PasswordEncoder dùng để mã hóa mật khẩu người dùng trước khi lưu vào cơ sở dữ liệu
    @Autowired
    private PasswordEncoder encoder;

    // cấu hình bảo mật cho ứng dụng
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //chuyển hướng cho người dùng muốn truy cập vào hệ thống vào trang đăng nhập (ở đây là formLogin)
        http.formLogin();
        //chỉ ra rằng phải xác thực với mọi yêu cầu để được enable
        http.authorizeRequests().anyRequest().authenticated();
    }
    // mã hóa mật khẩu người dùng
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    //sử dụng UserBuilder để sử dụng mã hóa mật khẩu người dùng trước khi lưu vào trong bô nhớ
    //
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        Collection<UserDetails> userDetails = new ArrayList<>();
        //passwordEncoder để mã hóa mật khẩu người dùng khi lưu vào trong bộ nhớ
        // phương thức encode() để truyền vào mã hóa mật khâu
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        var t1 = userBuilder.username("t1").password("1").roles("USER").build();
        userDetails.add(t1);
        return new InMemoryUserDetailsManager(userDetails);
    }
}
