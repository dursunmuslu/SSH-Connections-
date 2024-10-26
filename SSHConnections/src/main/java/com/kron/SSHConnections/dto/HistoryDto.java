package com.kron.SSHConnections.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class HistoryDto {
    private int id;
    private String userName;
    private String commandName;
    private String host;
    private LocalDateTime islemTarihi;

    public HistoryDto(String commandName, String host, String userName) {
        this.commandName = commandName;
        this.host = host;
        this.userName = userName;
        this.islemTarihi = LocalDateTime.now();
    }
}
