package com.spring.golub.service;

import com.spring.golub.entity.Hall;
import com.spring.golub.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HallService {
    @Autowired private HallRepository hallRepository;

    public List<Hall> getAll() {
        return hallRepository.findAll();
    }
}
