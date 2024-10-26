
package com.kron.SSHConnections.controller;

import com.kron.SSHConnections.dto.SshCommondRequest;
import com.kron.SSHConnections.service.SshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ssh")
public class SshController {

    @Autowired
    private SshService sshService;

    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody SshCommondRequest request) {
        try {
            String result = sshService.executeCommand(
                    request.getDeviceAdi(),
                    request.getHost(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getCommand()
            );
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error executing command: " + e.getMessage());
        }
    }
}
