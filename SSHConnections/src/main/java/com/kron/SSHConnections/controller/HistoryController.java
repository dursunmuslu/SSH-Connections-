package com.kron.SSHConnections.controller;

import com.kron.SSHConnections.dto.HistoryDto;
import com.kron.SSHConnections.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;
    private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/saveHistory")
    public ResponseEntity<HistoryDto> createHistory(@RequestBody HistoryDto historyDto) {
        logger.info("Received request to save history: {}", historyDto);
        HistoryDto createdHistory = historyService.saveHistory(historyDto);
        logger.info("History saved successfully: {}", createdHistory);
        return ResponseEntity.ok(createdHistory);
    }

    @GetMapping
    public ResponseEntity<List<HistoryDto>> getAllHistory() {
        logger.info("Received request to get all history records");
        List<HistoryDto> histories = historyService.getAllHistory();
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryDto> getHistoryById(@PathVariable int id) {
        logger.info("Received request to get history by ID: {}", id);
        HistoryDto historyDto = historyService.getHistoryById(id);
        return ResponseEntity.ok(historyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoryById(@PathVariable int id) {
        logger.info("Received request to delete history by ID: {}", id);
        historyService.deleteHistoryById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/username")
    public ResponseEntity<List<HistoryDto>> getHistoryByUsername(@RequestParam String username) {
        logger.info("Received request to get history by currentuser: {}", username);
        List<HistoryDto> histories = historyService.getHistoryByUsername(username);
        return ResponseEntity.ok(histories);
    }

    @GetMapping("/search/islemTarihi")
    public ResponseEntity<List<HistoryDto>> getHistoryByDate(@RequestParam String islemTarihi) {
        logger.info("Received request to get history by date: {}", islemTarihi);
        List<HistoryDto> histories = historyService.getHistoryByDate(islemTarihi);
        return ResponseEntity.ok(histories);
    }
}
