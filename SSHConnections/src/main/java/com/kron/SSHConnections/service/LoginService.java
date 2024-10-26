package com.kron.SSHConnections.service;

import com.kron.SSHConnections.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    LoginDto save(LoginDto loginDto);
    void delete(Long id);
    LoginDto findById(Long id);

    boolean authenticate(LoginDto loginDto);

}
