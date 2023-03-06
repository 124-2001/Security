package com.example.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;
@Configuration
@EnableWebSecurity
public class Config2 extends WebSecurityConfigurerAdapter {


    // cấu hình bảo mật cho ứng dụng
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //chuyển hướng cho người dùng muốn truy cập vào hệ thống vào trang đăng nhập (ở đây là formLogin)
        http.formLogin();
        //chỉ ra rằng phải xác thực với mọi yêu cầu để được enable
        http.authorizeRequests().anyRequest().authenticated();
    }
    //AuthenticationManagerBuilder là 1 cơ chế cấu hình thiết lập và xác thực người dùng
    // cung cấp nhiều phương thức cấu hình :
    //             inMemoryAuthentication(): cấu hình xác thực người dùng sử dụng in-memory.
    //             jdbcAuthentication(): cấu hình xác thực người dùng sử dụng cơ sở dữ liệu.
    //             ldapAuthentication(): cấu hình xác thực người dùng sử dụng LDAP (Lightweight Directory Access Protocol).
    //             userDetailsService(): cấu hình sử dụng một UserDetailsService để lấy thông tin người dùng.

    //cấu hình xác thực người dùng cho ứng dụng cung cấp 1 userDetailsService
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //gọi đối tượng auth sử dụng userDetailsService để lấy thông tin người dùng qua danh sách
        //inMemoryUserDetailsManager được cấu hình trong bộ nhớ ,
        // passwordEncoder dùng để chỉ định mã hóa mật khẩu người dùng của ứng dụng
        auth.userDetailsService(inMemoryUserDetailsManager())
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    // mã hóa mật khẩu người dùng
    @Bean
    public PasswordEncoder encoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    //khởi tạo 1 danh sach userDetails để lưu trữ dữ liệu tài khoản và trả về 1 InMemoryUserDetailsManager với danh sách userDetails
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        Collection<UserDetails> userDetails = new ArrayList<>();
        var t1 = User.withUsername("t1").password("1").authorities("read").build();
        userDetails.add(t1);
        return new InMemoryUserDetailsManager(userDetails);
    }
}
