package com.limo.ssiach2ex1.config;

import com.limo.ssiach2ex1.service.InMemoryUserDetailsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//用户管理配置
@Configuration
@EnableAsync
public class UserManagementConfig {
//    @Bean
//    public UserDetailsService userDetailsService() {
////        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
////
////        UserDetails user = User.withUsername("limo")
////                .password("12345")
////                .authorities("read")
////                .build();
////        userDetailsService.createUser(user);
//
//        UserDetails u = new User("limo", "12345", Collections.singleton(new SimpleGrantedAuthority("read")));
//
//        List<UserDetails> users = List.of(u);
//
//        return new InMemoryUserDetailsService(users);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        Map<String,PasswordEncoder> encoders=new HashMap<>();
//
//        encoders.put("noop",NoOpPasswordEncoder.getInstance());
//        encoders.put("bcrypt",new BCryptPasswordEncoder());
//        encoders.put("scrypt",SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
//
//        return new DelegatingPasswordEncoder("noop",encoders);
//    }

//    @Bean
//    public InitializingBean initializingBean(){
//        return () -> SecurityContextHolder.setStrategyName(
//                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
//        );
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
