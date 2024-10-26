package com.kron.SSHConnections.service.impl;

import com.kron.SSHConnections.dto.LoginDto;
import com.kron.SSHConnections.entity.Login;
import com.kron.SSHConnections.repo.LoginRepo;
import com.kron.SSHConnections.security.JwtTokenProvider;
import com.kron.SSHConnections.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginDto save(LoginDto loginDto) {
        Login login = new Login();
        login.setUsername(loginDto.getUsername());
        // Şifreyi hashleme işlemini gerçekleştirir
        login.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        Login savedLogin = loginRepo.save(login);
        loginDto.setId(savedLogin.getId());
        return loginDto;
    }

    @Override
    public void delete(Long id) {
        loginRepo.deleteById(id);
    }

    @Override
    public LoginDto findById(Long id) {
        return loginRepo.findById(id)
                .map(login -> {
                    LoginDto dto = new LoginDto();
                    dto.setId(login.getId());
                    dto.setUsername(login.getUsername());
                    // Şifreyi döndürme, güvenlik açısından gerekli değilse kaldırılabilir
                    dto.setPassword(login.getPassword());
                    return dto;
                }).orElse(null);
    }

    @Override
    public boolean authenticate(LoginDto loginDto) {
        try {
            // Kullanıcıyı kullanıcı adı ile bulma işlemini yapar
            Login login = loginRepo.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

            // Şifreyi kontrol eder
            if (passwordEncoder.matches(loginDto.getPassword(), login.getPassword())) {
                // Şifre doğru ise token oluşturulacak
                return true;
            }
            return false;
        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Kimlik doğrulama hatası: " + e.getMessage());
        }
    }

}
