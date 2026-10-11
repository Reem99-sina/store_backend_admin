package store_backend_admin.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import store_backend_admin.service.JwtService;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println(
                "JWT FILTER: " +
                request.getMethod() +
                " " +
                request.getRequestURI()
        );

        String authHeader = request.getHeader("Authorization");

        // No JWT → continue normally
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            if (jwtService.isTokenValid(token)) {

                String userId = jwtService.extractUserId(token);
                String role = jwtService.extractRole(token);

                System.out.println("✅ TOKEN VALID");
                System.out.println("USER ID = " + userId);
                System.out.println("ROLE = " + role);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("AUTH = " +
                        SecurityContextHolder.getContext().getAuthentication());

                System.out.println("AUTHORITIES = " +
                        SecurityContextHolder.getContext()
                                .getAuthentication()
                                .getAuthorities());

                System.out.println("IS AUTHENTICATED = " +
                        SecurityContextHolder.getContext()
                                .getAuthentication()
                                .isAuthenticated());
            } else {
                System.out.println("❌ TOKEN INVALID");
            }

        } catch (Exception e) {
            System.out.println("❌ JWT ERROR = " + e.getMessage());
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}