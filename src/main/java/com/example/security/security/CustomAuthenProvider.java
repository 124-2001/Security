package com.example.security.security;

import com.example.security.model.User;
import com.example.security.repository.UserDAO;
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
//    @Autowired
//    InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getRecordByName(userName);
        if(user!=null){
            if(user.getPassword()==password){
                //return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),userDetails.getAuthorities());
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (GrantedAuthority authority : user.getAuthorities()) {
                    authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
                }
                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),authorities);
            }
            else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
      return  authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

//    @Autowired  //Phải nối vào InMemoryUserDetailsManager để tìm user theo Username
//    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
//
//    @Autowired  //Dùng để kiểm tra password gửi lên trong login request với Hashed Password lưu trữ
//    private PasswordEncoder encoder;
//    //sử dụng để xác thực user bằng cách so sánh thông tin đăng nhập với thông tin được lưu trữ trước đó
//    // nhận vào một đối tượng Authentication chứa thông tin về userName ,password mà người dùng nhập vào
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        //lấy userName và pass của người dùng đã nhập
//        String username = authentication.getName();
//        String rawPassword = String.valueOf(authentication.getCredentials());
//        //lấy dữ liệu người dùng cho trước trong inMemoryUserDetailsManager
//        try {
//            //tìm user tương ứng
//            UserDetails userDetail = inMemoryUserDetailsManager.loadUserByUsername(username); //Tìm UserDetail theo Username
//            //nếu cùng tên so sách mật khẩu
//            if (encoder.matches(rawPassword, userDetail.getPassword())) { //So sánh password
//                //nếu đúng trả về  1 UsernamePasswordAuthenticationToken gồm tên đăng nhập mật khẩu và các quyên authorities
//                //UsernamePasswordAuthenticationToken dùng để xác thực truy cập của người dùng
//                return new UsernamePasswordAuthenticationToken(username, userDetail.getPassword(), userDetail.getAuthorities());
//            } else {
//                return null;
//            }
//        } catch (UsernameNotFoundException e) {
//            return null;
//        }
//    }
//
//    @Override // return true nếu CustomAuthenProvider hỗ trợ authentication kiểu Username, Password
//    public boolean supports(Class<?> authentication) {
//        //trả về true nếu authentication cung cấp 1 đối tượng UsernamePasswordAuthenticationToken, kiêm tra xem có phải cha của lớp
//        //được cung cấp (authencation) không qua phương thưqsc isAssignableFrom
//        //true _> quá trình xác thực thành công
//        // false -> quá trình xác thực thất bại
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }

}
