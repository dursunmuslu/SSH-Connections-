package com.kron.SSHConnections.entity;


import jakarta.persistence.*;
        import lombok.*;

@Entity
@Table(name = "credential")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})

public class Credential {

    @Id
    @SequenceGenerator(name = "seq_Credential", allocationSize = 1)
    @GeneratedValue(generator = "seq_Credantial", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, name = "DeviceAdı")
    private String deviceAdi;

    @Column(name = "Host")
    private String host;

    @Column(length = 100, name = "Username")
    private String username;

    @Column(length = 25, name = "Password")
    private String password;




}