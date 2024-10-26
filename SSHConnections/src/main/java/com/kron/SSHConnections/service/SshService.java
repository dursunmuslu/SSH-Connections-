package com.kron.SSHConnections.service;

public interface SshService {

    String executeCommand(String deviceAdi, String host, String username, String password, String command) throws Exception;

}
