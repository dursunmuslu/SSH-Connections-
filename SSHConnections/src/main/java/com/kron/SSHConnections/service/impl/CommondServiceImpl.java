package com.kron.SSHConnections.service.impl;

import com.kron.SSHConnections.dto.CommondDto;
import com.kron.SSHConnections.entity.Commond;
import com.kron.SSHConnections.repo.CommondRepo;
import com.kron.SSHConnections.service.CommondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommondServiceImpl implements CommondService {

    @Autowired
    private CommondRepo commondRepo;

    @Override
    public void saveCommand(Commond commond) {
        commondRepo.save(commond);
    }

    @Override
    public CommondDto save(CommondDto commondDto) {
        // DTO'dan entity'ye dönüştürme
        Commond commond = new Commond();
        commond.setId(commondDto.getId());
        commond.setCommandName(commondDto.getCommondName());
        commond.setCommandDescription(commondDto.getCommondDescription());

        // Entity'yi veritabanına kaydetme işlemini gerçekleştirir
        Commond savedCommond = commondRepo.save(commond);


        // Kayıt sonrası DTO oluşturma ve döndürme yapar
        return new CommondDto(
                savedCommond.getId(),
                savedCommond.getCommandName(),
                savedCommond.getCommandDescription()
        );
    }

    @Override
    public void delete(Long id) {
        commondRepo.deleteById(id);
    }

    @Override
    public List<CommondDto> getAll() {
        // Tüm komutları listeleme
        Iterable<Commond> commondsIterable = commondRepo.findAll();
        return StreamSupport.stream(commondsIterable.spliterator(), false)
                .map(commond -> new CommondDto(
                        commond.getId(),
                        commond.getCommandName(),
                        commond.getCommandDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommondDto> getAll(Pageable pageable) {
        // Sayfalama desteği ile tüm komutları listeleme
        Page<Commond> commondsPage = commondRepo.findAll(pageable);
        return commondsPage.getContent().stream()
                .map(commond -> new CommondDto(
                        commond.getId(),
                        commond.getCommandName(),
                        commond.getCommandDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public CommondDto getById(Long id) {
        // Verilen ID'ye sahip komutu getirme
        Commond commond = commondRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Komut bulunamadı"));

        return new CommondDto(
                commond.getId(),
                commond.getCommandName(),
                commond.getCommandDescription()
        );
    }
}
