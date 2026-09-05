package com.example.FOODHUB.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final CustomerDetailService customerDetailService;

    public SecurityConfiguration(
            JWTAuthenticationFilter jwtAuthenticationFilter,
            CustomerDetailService customerDetailService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customerDetailService = customerDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(customerDetailService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/auth/register",
                                "/auth/login"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.GET,
                                "/fooditems/**"
                        ).hasAnyRole(
                                "USER",
                                "SHOP_OWNER",
                                "ADMIN"
                        )

                        .requestMatchers(
                                HttpMethod.POST,
                                "/fooditems/**"
                        ).hasRole("SHOP_OWNER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/fooditems/**"
                        ).hasRole("SHOP_OWNER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/fooditems/**"
                        ).hasRole("SHOP_OWNER")

                        .requestMatchers(
                                "/cart/**"
                        ).hasRole("USER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/orders/**"
                        ).hasRole("USER")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/orders/**"
                        ).hasRole("USER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/orders/**"
                        ).hasRole("USER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/orders/**"
                        ).hasRole("USER")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/shops/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/shops/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/shops/**"
                        ).hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                .authenticationProvider(
                        authenticationProvider()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}