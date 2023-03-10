//package com.example.security.security;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//public class Config1 extends WebSecurityConfigurerAdapter {
//    //cấu hình cơ chế xác thực người dùng trong bộ nhớ với inMemoryAuthencation
//    //với tên đăng nhập , mạt khẩu , và quyền , sau đó là phương thức passwordEncoder để chỉ định
//    //việc lưu mật khẩu người dùng dưới dạng văn bản thường kh được mã hóa
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("nam").password("123")
//                .authorities("read").and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
//    // cấu hình bảo mật cho ứng dụng
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //chuyển hướng cho người dùng muốn truy cập vào hệ thống vào trang đăng nhập (ở đây là formLogin)
//        http.formLogin();
//        //chỉ ra rằng phải xác thực với mọi yêu cầu để được enable
//        http.authorizeRequests().anyRequest().authenticated();
//    }
//}
