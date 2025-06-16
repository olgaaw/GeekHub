package com.salesianos.geekhub.security;

import com.salesianos.geekhub.security.jwt.access.JwtAuthenticationFilter;
import com.salesianos.geekhub.security.jwt.exceptionhandling.JwtAccessDeniedHandler;
import com.salesianos.geekhub.security.jwt.exceptionhandling.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManager authenticationManager =
                authenticationManagerBuilder.authenticationProvider(authenticationProvider())
                        .build();

        return authenticationManager;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();

        p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder);
        return p;

    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(excepz -> excepz
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
        );
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET,  "/user/{id}", "post/user/{userId}").permitAll()
                .requestMatchers(HttpMethod.GET,   "/post/{id}", "/favourite/following/{userId}", "/favourite/followers/{userId}").authenticated()
                .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register", "/activate/account/", "/auth/refresh/token", "/auth/register/admin").permitAll()
                .requestMatchers(HttpMethod.POST, "/post/{postId}/like", "/favourite/add/{favouriteUserId}", "/post/").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/comment/{commentId}/delete/admin", "/post/{postId}/delete/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/like/{likeId}/deletebyUser", "/favourite/remove/{favouriteUserId}").authenticated()
                .requestMatchers(HttpMethod.PUT, "/interest/{id}").hasRole("ADMIN")
                .requestMatchers("/me/admin", "/user", "/interest").hasRole("ADMIN")
                .requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**", "/download/**").permitAll()
                .anyRequest().authenticated());


        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http.headers(headers ->
                headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost", "http://localhost:4200"));

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();

        configurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return configurationSource;
    }
}