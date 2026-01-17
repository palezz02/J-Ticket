package com.dev.j_ticket.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Core Security Configuration for J-Ticket.
 * This class defines the security posture of the application. It handles
 * authentication, authorization rules, and cryptographic standards for
 * protecting user data and ticket integrity in the J-Tycket system.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/**
     * Defines the password hashing strategy.
     * BCrypt is used to ensure industry-standard protection.
     * the passwords of the LC users.
     */
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	/**
     * Configures the HTTP security filter chain.
     * 1. Public endpoints (Swagger) are explicitly permitted.
     * 2. Business-critical endpoints require authentication.
     * 3. CSRF is disabled for stateless REST APIs using JWT/Tokens.
     */
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
	
	/**
     * In-Memory UserDetailsService for initial development and testing.
     * IMPORTANT: This implementation uses hardcoded credentials stored in volatile memory.
     * It is used to bypass database dependency during the early stages of the J-Ticket 
     * project. This must be replaced by a database-backed UserDetailsService 
     * before deploying the J-Tycket system to production.
     * * @return an InMemoryUserDetailsManager with a default admin user.
     */
	@Bean
	UserDetailsService userDetailsService() {
	    UserDetails user = User.builder()
	        .username("admin")
	        .password(passwordEncoder().encode("admin123"))
	        .roles("USER")
	        .build();

	    return new InMemoryUserDetailsManager(user);
	}
}
