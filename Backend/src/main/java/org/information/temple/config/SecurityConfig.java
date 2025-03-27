package org.information.temple.config;

import org.information.temple.serviceImpl.OAuth2UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final OAuth2UserServiceImpl oAuth2UserService;

    public SecurityConfig(OAuth2UserServiceImpl oAuth2UserService) {
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("🚀 SecurityConfig Loaded!");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/temples/**", "/oauth2/**").permitAll()  // Allow public access
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2UserService);

        return http.build();

    }

}
