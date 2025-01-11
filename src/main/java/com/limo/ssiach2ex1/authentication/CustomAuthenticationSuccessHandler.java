package com.limo.ssiach2ex1.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Optional<? extends GrantedAuthority> admin = authorities.stream()
                .filter(grantedAuthority -> {
                    System.out.println(
                            "当前用户的权限："+grantedAuthority.getAuthority()
                    );
                    return grantedAuthority.getAuthority().equalsIgnoreCase("admin");})
                .findFirst();

        if (admin.isPresent()) {
            response.sendRedirect("/home");
        }else {
            response.sendRedirect("/error403");
        }
    }
}
