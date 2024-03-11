//package com.shoppify.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//public class SecurityConfiguration {
//
//    public SecurityFilterChain filterChain(HttpSecurity https){
//        https
//                .csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .httpBasic();
//
//        return https.build()
//
//    }
//
//}
