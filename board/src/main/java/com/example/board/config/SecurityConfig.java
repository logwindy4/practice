package com.example.board.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Date;
import java.util.stream.DoubleStream;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests((auth) -> auth
//
                    .requestMatchers("/","/login","/board/login","/board/signup","/signup").permitAll()
                    .requestMatchers("/board/signup").permitAll()
                    .requestMatchers("/board/login").permitAll()
                    .requestMatchers("/board/list").permitAll()
                    .anyRequest().authenticated()
        );
        httpSecurity.formLogin((auth) -> auth
                    .loginPage("/board/login")
                    .loginProcessingUrl("/login")
                    .permitAll()
        );
        httpSecurity.csrf((auth) -> auth.disable()
        );
        return httpSecurity.build();
    }

    String secretKey = "YourSecretKey"; // JWT 서명을 위한 시크릿 키
    private Object SignatureAlgorithm;
    private DoubleStream Jwts;
    String token = Jwts.builder()
            .setSubject("username") // 페이로드에 사용자명 추가
            .setIssuedAt(new Date()) // 토큰 발급 시간 추가
            .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 토큰 만료 시간 추가
            .signWith(SignatureAlgorithm.HS256, secretKey) // 서명 알고리즘 및 시크릿 키 지정
            .compact();
}
