package com.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Đánh dấu 1 bean , là phương thức cấu hình trả về 1 UserDetailService
    // UserDetailService là 1 interface sử dụng để xác nhận thông tin đăng nhập người dùng
    // Cung cấp 1 phương thức duy nhất là loadUserByUserName để tìm kism thông tin đăng nhập của người dùng qua cơ sở dữ lệu hoặc danh sách được tạo trong bộ nhớ

    //    @Bean
//    public UserDetailsService userDetailsService() {
//        //tạo 1 đối tượng user sử dụng phương thức withUserName của lớp User với tên đăng nhập và mật khẩu , roles() là sử dụng để thêm vai trò của người dùng
//        //phương thức build là để xây dựng đối tượng từ các thông tin trên
//        var user1 = User.withUsername("tientho").password("123").roles()("read").build();
//        var user2 = User.withUsername("thotien").password("123456").roles()("read").build();
//        //trả về đối tượng InMemoryUserDetailsManager chứa 2 user đã tạo ở trên , để sử dụng quản lý thông tin
//        //khi đó khi khởi chạy chương trình 2 người dùng được tạo ra và lưu trữ trong bộ nhớ
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
//    //đánh dấu 1 bean , phương thức trả về đối tượng PasswordEncoder sử dụng để mã hóa mật khẩu người dùng
//    //phương thức trả về đối tượng NoOpPasswordEncoder là trả về mật khẩu không mã hóa
//    // có thể dùng nhiều password khác như  BCryptPasswordEncoder, SCryptPasswordEncoder, PasswordEncoderFactories
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    //để cấu hình loại xác thực  người dùng (ở đây là dùng InMemoryUserDetailsManager)
    //sử dụng 1 InMemoryUserDetailsManager đẻ quản lý danh sách người dùng
    //và không mã hóa mật khẩu
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager()).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    // cấu hình bảo mật cho ứng dụng
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //chuyển hướng cho người dùng muốn truy cập vào hệ thống vào trang đăng nhập (ở đây là formLogin)
        http.formLogin();
//        //chỉ ra rằng phải xác thực với mọi yêu cầu để được enable
//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests()
                .antMatchers("/api/user").hasAnyRole("USER","OPERATOR")
                .antMatchers("/api/operator").hasRole("OPERATOR")
                .anyRequest().hasRole("ADMIN");
    }
    //là 1 bean trong spring tạo ra 1 InMemoryUserDetailsManager để quản lý danh sách người dùng được lưu trong bộ nhớ
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        Collection<UserDetails> userDetails = new ArrayList<>();

        var t1 = User.withUsername("t1").password("1").roles("USER").build();
        var t2 = User.withUsername("t2").password("1").roles("ADMIN").build();
        var t3 = User.withUsername("t3").password("1").roles("OPERATOR").build();
        //tạo ra 1 đối tượng và đưa vào danh sách userDetails để lưu trữ
        userDetails.add(t1);
        userDetails.add(t2);
        userDetails.add(t3);
        //tạo 1 InMemoryUserDetailsManager với danh sách userDetails
        return new InMemoryUserDetailsManager(userDetails);
    }
}