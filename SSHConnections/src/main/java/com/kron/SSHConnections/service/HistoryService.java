package com.kron.SSHConnections.service;

import com.kron.SSHConnections.dto.HistoryDto;
import com.kron.SSHConnections.repo.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface HistoryService {

    HistoryDto saveHistory(HistoryDto historyDto);

    void recordHistory(String command, String host);

    List<HistoryDto> getAllHistory();
    HistoryDto getHistoryById(int id);
    void deleteHistoryById(int id);
    // Tarihe göre history kayıtlarını getirir
    List<HistoryDto> getHistoryByDate(String islemTarihi);

    // Kullanıcı adına göre history kayıtlarını getirir
    List<HistoryDto> getHistoryByUsername(String userName);
}
