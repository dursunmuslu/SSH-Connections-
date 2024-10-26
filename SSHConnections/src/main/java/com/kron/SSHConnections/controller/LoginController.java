package com.kron.SSHConnections.controller;

import com.kron.SSHConnections.dto.LoginDto;
import com.kron.SSHConnections.security.JwtTokenProvider;
import com.kron.SSHConnections.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/saveLogin")
    public ResponseEntity<? > createLogin(@RequestBody LoginDto loginDto) {
        LoginDto savedLogin = loginService.save(loginDto);
        String token = jwtTokenProvider.createToken(loginDto.getUsername());
        // Token ile birlikte Bearer prefix'ini döndür
        return ResponseEntity.ok("Bearer " + token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) {
        loginService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginDto> getLogin(@PathVariable Long id) {
        LoginDto loginDto = loginService.findById(id);
        return ResponseEntity.ok(loginDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        try {
            // Kullanıcı doğrulamasını yapar
            boolean isAuthenticated = loginService.authenticate(loginDto);
            if (isAuthenticated) {
                // Token oluşturulması
                String token = jwtTokenProvider.createToken(loginDto.getUsername());
                // Token ile birlikte Bearer prefix'ini döndür
                return ResponseEntity.ok("Bearer " + token);
            } else {
                // Yetkisiz kullanıcı yanıtı döndürür
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            // Hata mesajını loglama işlemini gerçekleştirir
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

}
