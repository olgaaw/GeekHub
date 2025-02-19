package com.salesianos.geekhub.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;










}