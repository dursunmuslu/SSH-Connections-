package com.kron.SSHConnections.controller;

import com.kron.SSHConnections.dto.CommondDto;
import com.kron.SSHConnections.service.CommondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commands")
public class CommondController {

    @Autowired
    private CommondService commondService;

    @PostMapping("/saveCommands")
    public ResponseEntity<CommondDto> createCommond(@RequestBody CommondDto commondDto) {
        CommondDto savedCommond = commondService.save(commondDto);
        return ResponseEntity.ok(savedCommond);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommond(@PathVariable Long id) {
        commondService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getCommands")
    public ResponseEntity<List<CommondDto>> getAllCommonds() {
        List<CommondDto> commonds = commondService.getAll();
        return ResponseEntity.ok(commonds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommondDto> getCommondById(@PathVariable Long id) {
        CommondDto commond = commondService.getById(id);
        return ResponseEntity.ok(commond);
    }
}
