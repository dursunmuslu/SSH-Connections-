package com.kron.SSHConnections.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CommondDto {

    private Long id;
    private String commondName;
    private String commondDescription;

}
