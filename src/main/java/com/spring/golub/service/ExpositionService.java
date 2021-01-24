package com.spring.golub.service;

import com.spring.golub.entity.Exposition;
import com.spring.golub.repository.ExpositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final ExpositionRepository expositionRepository;

    public Page<Exposition> getAllExpositions(Pageable pageable) {
        return expositionRepository.findAll(pageable);
    }
}
