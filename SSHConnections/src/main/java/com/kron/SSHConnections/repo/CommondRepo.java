package com.kron.SSHConnections.repo;

import com.kron.SSHConnections.entity.Commond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Optional;

@Repository

public interface CommondRepo extends JpaRepository<Commond, Long> {


}


