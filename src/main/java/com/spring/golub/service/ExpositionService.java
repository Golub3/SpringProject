package com.spring.golub.service;

import com.spring.golub.entity.Exposition;
import com.spring.golub.entity.User;
import com.spring.golub.repository.ExpositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpositionService {
    @Autowired private ExpositionRepository expositionRepository;

    public List<Exposition> getAll() {
        return expositionRepository.findAll();
    }

    public void saveExposition(Exposition exposition) {
        this.expositionRepository.save(exposition);
    }

    public Page<Exposition> getAllExpositions(Pageable pageable) {
        return expositionRepository.findAll(pageable);
    }

    public Optional<Exposition> findById(long id) {
        return expositionRepository.findById(id);
    }
}
