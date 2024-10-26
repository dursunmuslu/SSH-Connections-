package com.kron.SSHConnections.repo;

import com.kron.SSHConnections.entity.Commond;
import com.kron.SSHConnections.entity.Credential;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CredentialRepo extends JpaRepository<Credential, Long> {



}
