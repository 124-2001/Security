package com.example.security.security;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//AuthenticationProvider là interface sử dụng để xác thực người dùng khi người dùng cung cấp username và pass
//phỉa thực hiện phương thức authenticate để cung cấp các thông tin chi tiết
//để cấu hình cần phải cấu hình trong lớp kết thừa từ WebSecurityConfigurerAdapter
@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
     UserRepository userRepository;

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String userName = authentication.getName();
//        String pass = String.valueOf(authentication.getCredentials());
//        UserDetails user = userRepository.findByUsername(userName);
//        if(user!=null){
//            if(encoder.matches(pass,user.getPassword())){
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                for (GrantedAuthority authority : user.getAuthorities()) {
//                    authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//                }
//                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),authorities);
//            }
//        }
//        return null;


        String username = authentication.getName();
        String rawPassword = String.valueOf(authentication.getCredentials());
        try {
            UserDetails userDetail = inMemoryUserDetailsManager.loadUserByUsername(username);
            if (rawPassword.equals(userDetail.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, userDetail.getPassword(), userDetail.getAuthorities());
            } else {
                return null;
            }
        } catch (UsernameNotFoundException e) {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
      return  authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
