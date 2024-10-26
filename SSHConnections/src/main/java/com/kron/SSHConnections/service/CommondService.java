package com.kron.SSHConnections.service;

import com.kron.SSHConnections.dto.CommondDto;
import com.kron.SSHConnections.entity.Commond;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CommondService {
    void saveCommand(Commond commond);
    CommondDto save (CommondDto commondDto);
    void delete (Long id);
    List<CommondDto> getAll();
    List<CommondDto> getAll(Pageable pageable);


    CommondDto getById(Long id);
}
