package com.algorizin.mentorship.authorization.jwt.config;

import com.algorizin.mentorship.authorization.permission.entity.PermissionEntity;
import com.algorizin.mentorship.authorization.permission.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    public WebSecurityConfig() {
        super();
    }

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
//
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(encoder);
    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        List<PermissionEntity> urlPermissionList = permissionRepository.findAll();
//        http.cors().disable();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        for (PermissionEntity urlPermission : urlPermissionList) {
//            if (urlPermission.getPermissionName().equalsIgnoreCase("all")) {
//                httpSecurity.authorizeRequests().antMatchers(HttpMethod.valueOf(urlPermission.getMethod()), urlPermission.getUrl())
//                        .permitAll();
//            } else {
//                httpSecurity.authorizeRequests()
//                        .antMatchers(HttpMethod.valueOf(urlPermission.getMethod()), urlPermission.getUrl())
//                        .hasAuthority(urlPermission.getPermissionName());
//            }
//        }
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
