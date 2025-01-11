package com.limo.ssiach2ex1.config;

import com.limo.ssiach2ex1.authentication.CustomAuthenticationFailureHandler;
import com.limo.ssiach2ex1.authentication.CustomAuthenticationSuccessHandler;
import com.limo.ssiach2ex1.authentication.CustomEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

//授权配置
@Configuration
public class WebAuthorizationConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider);

        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorize->authorize
                                .requestMatchers("/error401","/error403").permitAll()
                .anyRequest()
                .authenticated()
        )
            .formLogin(
                    formLogin->formLogin
                            .permitAll()
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler)
                    //.permitAll()
                    //.loginPage()
//                    .defaultSuccessUrl("/home",true)
//                    .failureUrl("/error401")
            )
                .httpBasic(httpBasic -> {
            // 配置 HTTP Basic 验证相关设置
            httpBasic.realmName("other");
            httpBasic.authenticationEntryPoint(new CustomEntryPoint());
        });


        return http.build();
    }

}
