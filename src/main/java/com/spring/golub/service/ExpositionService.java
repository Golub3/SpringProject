package com.spring.golub.service;

import com.spring.golub.entity.Exposition;
import com.spring.golub.entity.Hall;
import com.spring.golub.entity.Schedule;
import com.spring.golub.repository.ExpositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
