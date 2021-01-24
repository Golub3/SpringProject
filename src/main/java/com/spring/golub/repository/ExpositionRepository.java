package com.spring.golub.repository;

import com.spring.golub.entity.Exposition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Repository
public interface ExpositionRepository extends JpaRepository<Exposition, Long> {


//    @Query(value = "SELECT exposition.theme, hall.name, schedule.date, schedule.time_start, schedule.time_end, exposition.price\n" +
//            "FROM exposition \n" +
//            "INNER JOIN schedule \n" +
//            "ON exposition.id_exp = schedule.id_e\n" +
//            "INNER JOIN hall \n" +
//            "ON schedule.id_h = hall.id_hall\n" +
//            "order by exposition.id_exp asc limit ?"
//            , nativeQuery = true)
//    Page<Exposition> findAllExpositions(Pageable pageable);

}
