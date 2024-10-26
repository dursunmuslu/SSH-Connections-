package com.kron.SSHConnections;

import com.kron.SSHConnections.service.SshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SshConnectionsApplication implements CommandLineRunner {

	@Autowired
	private SshService sshService;

	public static void main(String[] args) {
		SpringApplication.run(SshConnectionsApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
