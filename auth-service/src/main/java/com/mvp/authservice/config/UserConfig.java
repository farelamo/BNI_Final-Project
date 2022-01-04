package com.mvp.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

//    @Bean
//    public UserDetailsService users(){
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$Y1BJSKc4tGt.cSlrXLHd/.QjPBNJ3B6//f6xLHM8bKsUjT2FTRwdS")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails member = User.builder()
//                .username("member")
//                .password("{bcrypt}$2a$12$lm1Ab8IpCtqyEq/L.wCQx.u9FVEOM0lXLsd.GXHefBROh254oxJ2y")
//                .roles("MEMBER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, member);
//    }
}
