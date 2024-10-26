package com.kron.SSHConnections.entity;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(name = "commond")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Commond {

    @Id
    @SequenceGenerator(name = "seq_Commond", sequenceName = "seq_Commond", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Commond")
    private Long id;

    @Column(length = 100, name = "CommandName")
    private String commandName;

    @Column(length = 255, name = "CommandDescription")
    private String commandDescription;

}
