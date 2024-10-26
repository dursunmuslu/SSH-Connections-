package com.kron.SSHConnections.repo;

import com.kron.SSHConnections.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<History, Integer> {
    // Tarihe göre history kayıtlarını getirir
    List<History> findByIslemTarihi(LocalDateTime islemTarihi);

    // Kullanıcı adına göre history kayıtlarını getirir
    List<History> findByUserName(String userName);



}
