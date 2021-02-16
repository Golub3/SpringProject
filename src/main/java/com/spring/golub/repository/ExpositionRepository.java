package com.spring.golub.repository;

import com.spring.golub.entity.Exposition;
import com.spring.golub.entity.Hall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ExpositionRepository extends JpaRepository<Exposition, Long> {
    List<Exposition> findAll();

}
