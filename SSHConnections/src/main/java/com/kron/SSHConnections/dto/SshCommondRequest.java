
package com.kron.SSHConnections.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SshCommondRequest {

    private String deviceAdi;
    private String host;
    private String username;
    private String password;
    private String command;



}
