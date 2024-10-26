package com.kron.SSHConnections.service.impl;

import com.kron.SSHConnections.dto.HistoryDto;
import com.kron.SSHConnections.entity.History;
import com.kron.SSHConnections.repo.HistoryRepo;
import com.kron.SSHConnections.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepo historyRepo;

    @Autowired
    public HistoryServiceImpl(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    @Override
    public HistoryDto saveHistory(HistoryDto historyDto) {
        // Mevcut aktif kullanıcıyı al
        String currentUsername = getCurrentUsername();

        // Yeni bir History entity oluşturur
        History history = new History();
        history.setUserName(currentUsername);
        history.setCommandName(historyDto.getCommandName());
        history.setHost(historyDto.getHost());

        // İşlem tarihini otomatik olarak ayarlar
        LocalDateTime islemTarihi = LocalDateTime.now();
        history.setIslemTarihi(islemTarihi);

        // History kaydını veritabanına kaydet
        History savedHistory = historyRepo.save(history);

        // Kaydedilen kaydı DTO'ya dönüştür
        return mapToDto(savedHistory);
    }

    @Override
    public void recordHistory(String command, String host) {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setCommandName(command);
        historyDto.setHost(host);
        saveHistory(historyDto);
    }

    @Override
    public List<HistoryDto> getAllHistory() {
        return historyRepo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HistoryDto getHistoryById(int id) {
        History history = historyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("History not found"));
        return mapToDto(history);
    }

    @Override
    public void deleteHistoryById(int id) {
        historyRepo.deleteById(id);
    }

    @Override
    public List<HistoryDto> getHistoryByDate(String islemTarihiStr) {
        LocalDateTime islemTarihi = LocalDateTime.parse(islemTarihiStr);
        return historyRepo.findByIslemTarihi(islemTarihi).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<HistoryDto> getHistoryByUsername(String username) {
        return historyRepo.findByUserName(username).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "anonymousUser";
    }


    private HistoryDto mapToDto(History history) {
        HistoryDto historyDto = new HistoryDto();
        historyDto.setId(Math.toIntExact(history.getId()));
        historyDto.setUserName(history.getUserName());
        historyDto.setCommandName(history.getCommandName());
        historyDto.setHost(history.getHost());
        historyDto.setIslemTarihi(history.getIslemTarihi());
        return historyDto;
    }
}
