package com.kron.SSHConnections.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})


public class Login {
    @Id
    @SequenceGenerator(name = "seq_Login", allocationSize = 1)
    @GeneratedValue(generator = "seq_Login", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "Username",unique = true)
    @NotEmpty
    private String username;

    @Column(length = 100, name = "Password")
    @NotEmpty
    private String password;


}
