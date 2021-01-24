package com.spring.golub.repository;

import com.spring.golub.entity.Exposition;
import com.spring.golub.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {



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
