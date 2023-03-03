package com.example.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Đánh dấu 1 bean , là phương thức cấu hình trả về 1 UserDetailService
    // UserDetailService là 1 interface sử dụng để xác nhận thông tin đăng nhập người dùng
    // Cung cấp 1 phương thức duy nhất là loadUserByUserName để tìm kism thông tin đăng nhập của người dùng qua cơ sở dữ lệu hoặc danh sách được tạo trong bộ nhớ

    @Bean
    public UserDetailsService userDetailsService() {
        //tạo 1 đối tượng user sử dụng phương thức withUserName của lớp User với tên đăng nhập và mật khẩu , authorities là sử dụng để thêm vai trò của người dùng
        //phương thức build là để xây dựng đối tượng từ các thông tin trên
        var user1 = User.withUsername("tientho").password("123").authorities("read").build();
        var user2 = User.withUsername("thotien").password("123456").authorities("read").build();
        //trả về đối tượng InMemoryUserDetailsManager chứa 2 user đã tạo ở trên , để sử dụng quản lý thông tin
        //khi đó khi khởi chạy chương trình 2 người dùng được tạo ra và lưu trữ trong bộ nhớ
        return new InMemoryUserDetailsManager(user1, user2);
    }
    //đánh dấu 1 bean , phương thức trả về đối tượng PasswordEncoder sử dụng để mã hóa mật khẩu người dùng
    //phương thức trả về đối tượng NoOpPasswordEncoder là trả về mật khẩu không mã hóa
    // có thể dùng nhiều password khác như  BCryptPasswordEncoder, SCryptPasswordEncoder, PasswordEncoderFactories
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}