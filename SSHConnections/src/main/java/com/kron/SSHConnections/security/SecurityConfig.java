package com.kron.SSHConnections.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .disable() // CSRF korumasını devre dışı bırakmak için
                )
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/users/login", "/users/saveLogin", "/api/ssh/execute" ,"api/history/saveHistory","/credentials/getCredential", "/commands/getCommands","/credentials/saveDevice" +
                                "").permitAll() // Bu endpoint'lere herkes erişebilir
                        //.requestMatchers("/api/ssh/execute").authenticated() // Bu endpoint'lere yetki gerektir
                        .anyRequest().authenticated() // Diğer tüm endpoint'ler için kimlik doğrulaması gerektir
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

