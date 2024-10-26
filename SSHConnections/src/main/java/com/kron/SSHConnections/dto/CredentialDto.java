package com.kron.SSHConnections.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialDto {

    private Long id;
    private String deviceAdi;
    private String username;
    private String password;
    private String host;


    public CredentialDto(Long id, String username, String password, String host, String deviceAdi) {
        this.id = id;
        this.deviceAdi = deviceAdi;
        this.username = username;
        this.password = password;
        this.host = host;
    }
}
