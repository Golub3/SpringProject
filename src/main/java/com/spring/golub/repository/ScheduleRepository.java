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

//    @Query(value = "SELECT * FROM SCHEDULE WHERE DATE >= :dateStart AND date <= :dateEnd ORDER BY :sortField", nativeQuery = true)
//    List<Schedule> getAllBetweenDates(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd,
//                                      @Param("sortField") String sortField);

    @Query(value = "SELECT * FROM SCHEDULE, EXPOSITION WHERE schedule.id_e = exposition.id_exp and DATE >= ?1 AND DATE <= ?2", nativeQuery = true)
    Page<Schedule> getAllBetweenDates(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd,
                                      Pageable pageable);


    void deleteById(long id);

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
