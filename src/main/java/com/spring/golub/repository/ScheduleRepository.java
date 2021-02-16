package com.spring.golub.repository;

import com.spring.golub.entity.Exposition;
import com.spring.golub.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT * FROM SCHEDULE, EXPOSITION WHERE schedule.id_e = exposition.id_exp and DATE >= ?1 AND DATE <= ?2", nativeQuery = true)
    Page<Schedule> getAllBetweenDates(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd,
                                      Pageable pageable);


    void deleteById(long id);

}
