package com.kron.SSHConnections.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "History")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class History {

    @Id
    @SequenceGenerator(name = "seq_History", sequenceName = "seq_History", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_History")
    private Long id;

    @Column(length = 100, name = "Username")
    private String userName;

    @Column(length = 100, name = "Komut Adı")
    private String commandName;

    @Column(name = "Cihaz ip")
    private String host;

    @Column(name = "İşlem Tarihi")
    private LocalDateTime islemTarihi;




}
