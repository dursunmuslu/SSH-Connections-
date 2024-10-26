
package com.kron.SSHConnections.controller;

import com.kron.SSHConnections.dto.CredentialDto;
import com.kron.SSHConnections.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credentials")
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @PostMapping("/saveDevice")
    public ResponseEntity<CredentialDto> createCredential(@RequestBody CredentialDto credentialDto) {
        CredentialDto savedCredential = credentialService.save(credentialDto);
        return ResponseEntity.ok(savedCredential);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredential(@PathVariable Long id) {
        credentialService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getCredential")
    public ResponseEntity<List<CredentialDto>> getAllCredentials() {
        List<CredentialDto> credentials = credentialService.getAll();
        return ResponseEntity.ok(credentials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialDto> getCredentialById(@PathVariable Long id) {
        CredentialDto credential = credentialService.getById(id);
        return ResponseEntity.ok(credential);
    }
}
