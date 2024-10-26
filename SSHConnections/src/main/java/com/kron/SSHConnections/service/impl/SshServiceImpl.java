package com.kron.SSHConnections.service.impl;

import com.kron.SSHConnections.dto.HistoryDto;
import com.kron.SSHConnections.entity.Commond;
import com.kron.SSHConnections.entity.Credential;
import com.kron.SSHConnections.service.HistoryService;
import com.kron.SSHConnections.service.CommondService;
import com.kron.SSHConnections.service.CredentialService;
import com.kron.SSHConnections.service.SshService;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Service
public class SshServiceImpl implements SshService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private CommondService commondService;

    @Autowired
    private CredentialService credentialService;

    @Override
    public String executeCommand(String deviceAdi, String host, String username, String password, String command) throws Exception {
        JSch jsch = new JSch();
        Session session = null;
        ChannelExec channelExec = null;
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();

        try {
            session = jsch.getSession(username, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);
            reader = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));

            channelExec.connect();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            Credential credential = new Credential();
            credential.setDeviceAdi(deviceAdi);
            credential.setUsername(username);
            credential.setPassword(password);
            credentialService.saveCredential(credential);

            Commond commond = new Commond();
            commond.setCommandName(command);
            commond.setCommandDescription("Command executed on device IP: " + host);
            commondService.saveCommand(commond);

            HistoryDto historyDto = new HistoryDto();
            historyDto.setCommandName(command);
            historyDto.setHost(host);
            historyDto.setUserName(getCurrentUsername());
            historyDto.setIslemTarihi(LocalDateTime.now());
            historyService.saveHistory(historyDto);

        } catch (Exception e) {
            throw new Exception("Error executing SSH command: " + e.getMessage(), e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (channelExec != null) {
                channelExec.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }

        return result.toString();
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "anonymousUser";
    }
}
