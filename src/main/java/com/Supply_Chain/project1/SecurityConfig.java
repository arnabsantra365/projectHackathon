package com.Supply_Chain.project1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/suppliers/**","api/products/**","api/orders/**").permitAll() // Allow public access to /accounts
                        .anyRequest().authenticated() // Secure other endpoints
                );

        return http.build();
    }
}
