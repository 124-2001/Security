//package com.example.security.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Config4 extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private PasswordEncoder encoder;
//    //httpBasic là kiểu xác thực đơn giản , khi truy cập 1 yêu cầu thì trình duyệt yêu cầu nhập mật khẩu bằng 1 hợp thoại
//    //httpLogin tạo 1 trang đăng nhập dể yêu cầu đăng nhập bằng tài khoản  mật khẩu như biểu mẫu
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.authorizeRequests().anyRequest().authenticated();
//    }
//    //dùng để mã hóa mật khẩu dựa trên các loài mã chỉ định (encodingTyoe) , sử dụng
//    //lớp DelegatingPasswordEncoder để xác định mã hóa cụ thể để sử dụng
//    public static PasswordEncoder delegatePasswordEncoder(String encodingType){
//        //sử dụng map để ánh xạ các loại mã và đối tượng tương ứng
//        //giúp sự dụng giữa các loại mã dễ dàng
//        Map<String,PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("bcrypt",new BCryptPasswordEncoder());
//        encoders.put("pbkdf2",new Pbkdf2PasswordEncoder());
//
//        return new DelegatingPasswordEncoder(encodingType,encoders);
//    }
//
//    @Bean
//    public PasswordEncoder encoder(){
//        return Config4.delegatePasswordEncoder("pbkdf2");
//    }
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        Collection<UserDetails> userDetails = new ArrayList<>();
//        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//        var t1 = userBuilder.username("t1").password("1").roles("USER").build();
//        userDetails.add(t1);
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//}
