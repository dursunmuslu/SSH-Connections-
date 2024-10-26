
package com.kron.SSHConnections.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String DEFAULT_FILTER_URL = "/saveLogin";

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        super(new AntPathRequestMatcher(DEFAULT_FILTER_URL));
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        if (authResult != null) {
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String username = jwtTokenProvider.getUsername(token);
            if (username != null && jwtTokenProvider.validateToken(token)) {
                return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            }
        }
        // Token doğrulama başarısız olursa, AuthenticationException fırlatılır.
        throw new AuthenticationException("Invalid token") {};
    }
}
