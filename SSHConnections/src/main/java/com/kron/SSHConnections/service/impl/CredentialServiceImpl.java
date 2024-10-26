package com.kron.SSHConnections.service.impl;

import com.kron.SSHConnections.dto.CommondDto;
import com.kron.SSHConnections.dto.CredentialDto;
import com.kron.SSHConnections.entity.Commond;
import com.kron.SSHConnections.entity.Credential;
import com.kron.SSHConnections.repo.CredentialRepo;
import com.kron.SSHConnections.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepo credentialRepo;

    @Override
    public void saveCredential(Credential credential) {
        credentialRepo.save(credential);
    }

    @Override
    public List<CredentialDto> getAllCredentials(Pageable pageable) {
        Page<Credential> credentialsPage = credentialRepo.findAll(pageable);
        return credentialsPage.stream()
                .map(credential -> new CredentialDto(
                        credential.getId(),
                        credential.getDeviceAdi(),
                        credential.getUsername(),
                        credential.getPassword(),
                        credential.getHost()))
                .collect(Collectors.toList());

    }

    @Override
    public List<CredentialDto> getAllCredentials() {
        Iterable<Credential> credentialsIterable = credentialRepo.findAll();
        return StreamSupport.stream(credentialsIterable.spliterator(), false)
                .map(credential -> new CredentialDto(
                        credential.getId(),
                        credential.getDeviceAdi(),
                        credential.getUsername(),
                        credential.getPassword(),
                        credential.getHost()))
                .collect(Collectors.toList());

    }



    @Override
    public CredentialDto save(CredentialDto credentialDto) {
        Credential credential = new Credential();
        credential.setId(credentialDto.getId());
        credential.setDeviceAdi(credentialDto.getDeviceAdi());
        credential.setUsername(credentialDto.getUsername());
        credential.setPassword(credentialDto.getPassword());
        credential.setHost(credentialDto.getHost());

        Credential savedCredential = credentialRepo.save(credential);

        return new CredentialDto(
                savedCredential.getId(),
                savedCredential.getDeviceAdi(),
                savedCredential.getUsername(),
                savedCredential.getPassword(),
                savedCredential.getHost()
        );
    }

    @Override
    public List<CredentialDto> getAll() {
        Iterable<Credential> credentialsIterable = credentialRepo.findAll();
        return StreamSupport.stream(credentialsIterable.spliterator(), false)
                .map(credential -> new CredentialDto(
                        credential.getId(),
                        credential.getDeviceAdi(),
                        credential.getUsername(),
                        credential.getPassword(),
                        credential.getHost()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CredentialDto> getAll(Pageable pageable) {
        Page<Credential> credentialsPage = credentialRepo.findAll(pageable);
        return credentialsPage.stream()
                .map(credential -> new CredentialDto(
                        credential.getId(),
                        credential.getDeviceAdi(),
                        credential.getUsername(),
                        credential.getPassword(),
                        credential.getHost()))
                .collect(Collectors.toList());
    }

    @Override
    public CredentialDto getById(Long id) {
        Optional<Credential> credentialOptional = credentialRepo.findById(id);
        return credentialOptional
                .map(credential -> new CredentialDto(
                        credential.getId(),
                        credential.getDeviceAdi(),
                        credential.getUsername(),
                        credential.getPassword(),
                        credential.getHost()))
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        credentialRepo.deleteById(id);
    }
}
