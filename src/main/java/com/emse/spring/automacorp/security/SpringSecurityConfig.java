package com.emse.spring.automacorp.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableMethodSecurity
@Configuration
public class SpringSecurityConfig {

    public static final String ROLE_USER = "USER";

    public static final String ROLE_ADMIN = "ADMIN";

    @Bean
    public UserDetailsService userDetailsService() {
        // We create a password encoder
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user").password(encoder.encode("myPassword")).roles(ROLE_USER).build()
        );
        manager.createUser(
                User.withUsername("admin").password(encoder.encode("myPassword")).roles(ROLE_USER,ROLE_ADMIN).build()
        );
        return manager;
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().hasRole(ROLE_USER));
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    @Order
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).authenticated() //
                        .anyRequest().permitAll() //
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String currentPrincipalName = authentication.getName();
}