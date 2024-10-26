package com.kron.SSHConnections.repo;

import com.kron.SSHConnections.entity.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends CrudRepository<Login, Long> {
    Optional<Login> findByUsername(String username);

}
