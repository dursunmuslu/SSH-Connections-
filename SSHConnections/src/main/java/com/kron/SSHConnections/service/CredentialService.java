package com.kron.SSHConnections.service;

import com.kron.SSHConnections.dto.CredentialDto;
import com.kron.SSHConnections.entity.Credential;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CredentialService {
    List<CredentialDto> getAllCredentials(Pageable pageable);

    List<CredentialDto> getAllCredentials();
    CredentialDto save(CredentialDto credentialDto);
    void delete(Long id);
    List<CredentialDto> getAll();
    List<CredentialDto> getAll(Pageable pageable);
    CredentialDto getById(Long id);
    void saveCredential(Credential credential);
}
