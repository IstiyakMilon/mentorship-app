package com.algorizin.mentorship.authorization.jwt.service;

import com.algorizin.mentorship.authorization.jwt.dto.UserDTO;
import com.algorizin.mentorship.authorization.user.entity.LoginEntity;
import com.algorizin.mentorship.authorization.user.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginEntity user = this.loginRepository.findByUserId(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(permission -> permission.getPermissionName())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return buildUserForAuthentication(user, authorities);
    }
    private UserDetails buildUserForAuthentication(LoginEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(),authorities);
    }

//    public LoginEntity saveUser(UserDTO user) {
//        LoginEntity newUser = new LoginEntity();
//        newUser.setUserId(user.getUserId());
//        newUser.setPassword(encoder.encode(user.getPassword()));
//        newUser.setRole(user.getRole());
//        newUser.setStatus(user.getStatus());
//        newUser.setCreatedOn(new Timestamp(new Date().getTime()));
//        return loginRepository.save(newUser);
//    }
}
