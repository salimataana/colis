package com.github.ana.deliverymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
/*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:5173"); // Autoriser le frontend
        config.addAllowedMethod("*"); // Autoriser toutes les méthodes HTTP
        config.addAllowedHeader("*"); // Autoriser tous les en-têtes
        source.registerCorsConfiguration("/**", config); // Appliquer à toutes les routes
        return new CorsFilter(source);
    }
*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        if(false) {
            return http
                    .authorizeHttpRequests(auth-> auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/register").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/rest/v1/**").permitAll()
                            .requestMatchers("/**").permitAll()
                            .anyRequest() .authenticated()
                    )
                    .formLogin(form-> form
                            .defaultSuccessUrl("/home", true)
                    )
                    .logout(config -> config.logoutSuccessUrl("/home"))
                    .build();
        }

        return
                http .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/rest/v1/**").permitAll()).csrf(csrf ->csrf.disable()).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
