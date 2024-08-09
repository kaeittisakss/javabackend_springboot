package com.example.javabackend.config;

import org.hibernate.StatelessSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().disable().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeRequests().antMatchers("/user/register","/user/login").anonymous()
//                .anyRequest().authenticated();
//    }

    // รูปแบบโค้ด version ใหม่ / Spring Security เวอร์ชันใหม่
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable()) // ใช้ฟังก์ชันใหม่สำหรับปิด CORS
                .csrf(csrf -> csrf.disable()) // ใช้ฟังก์ชันใหม่สำหรับปิด CSRF
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/user/register", "/user/login").anonymous()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
