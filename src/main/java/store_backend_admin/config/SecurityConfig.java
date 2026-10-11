package store_backend_admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import store_backend_admin.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                // =========================
                // SECURITY
                // =========================

                .csrf(csrf -> csrf.disable())

                .formLogin(form -> form.disable())

                .httpBasic(basic -> basic.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // =========================
                // AUTHORIZATION
                // =========================

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // AUTH
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/auth/register",
                                "/auth/login"
                        ).permitAll()


                        // =========================
                        // CATEGORIES
                        // =========================

                        // Public
                        .requestMatchers(
                                HttpMethod.GET,
                                "/categories",
                                "/categories/*"
                        ).permitAll()

                        // Admin
                        .requestMatchers(
                                HttpMethod.POST,
                                "/categories"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/categories/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/categories/*"
                        ).hasRole("ADMIN")


                        // =========================
                        // PRODUCTS
                        // =========================

                        // Public
                        .requestMatchers(
                                HttpMethod.GET,
                                "/products",
                                "/products/*"
                        ).permitAll()

                        // Admin
                        .requestMatchers(
                                HttpMethod.POST,
                                "/products"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/products/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/products/*"
                        ).hasRole("ADMIN")


                        // =========================
                        // USERS
                        // =========================

                        // Admin
                        .requestMatchers(
                                HttpMethod.POST,
                                "/users"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/users"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/users/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/users/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/users/*"
                        ).hasRole("ADMIN")

                        // Authenticated user
                        .requestMatchers(
                                HttpMethod.GET,
                                "/users/me"
                        ).authenticated()


                        // =========================
                        // CAMPAIGNS
                        // =========================

                        // Public
                        .requestMatchers(
                                HttpMethod.GET,
                                "/campaigns",
                                "/campaigns/*"
                        ).permitAll()

                        // Admin
                        .requestMatchers(
                                HttpMethod.POST,
                                "/campaigns"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/campaigns/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/campaigns/*"
                        ).hasRole("ADMIN")


                        // =========================
                        // PROMOTIONS
                        // =========================

                        // Public
                        .requestMatchers(
                                HttpMethod.GET,
                                "/promotions",
                                "/promotions/*"
                        ).permitAll()

                        // Admin
                        .requestMatchers(
                                HttpMethod.POST,
                                "/promotions"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/promotions/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/promotions/*"
                        ).hasRole("ADMIN")


                        // =========================
                        // INVENTORY
                        // =========================

                        // Public
                        .requestMatchers(
                                HttpMethod.GET,
                                "/inventory",
                                "/inventory/*",
                                "/inventory/product/*"
                        ).permitAll()

                        // Admin
                        .requestMatchers(
                                HttpMethod.POST,
                                "/inventory"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/inventory/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/inventory/*"
                        ).hasRole("ADMIN")


                        // =========================
                        // EVERYTHING ELSE
                        // =========================

                        .anyRequest().authenticated()
                )

                // =========================
                // JWT FILTER
                // =========================

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}