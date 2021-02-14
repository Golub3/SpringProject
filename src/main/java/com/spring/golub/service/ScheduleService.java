package com.spring.golub.service;

import com.spring.golub.entity.Exposition;
import com.spring.golub.entity.Schedule;
import com.spring.golub.repository.ExpositionRepository;
import com.spring.golub.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
    @Autowired private ScheduleRepository scheduleRepository;

    public Page<Schedule> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public void saveSchedule(Schedule schedule) {
        this.scheduleRepository.save(schedule);
    }


    public Page<Schedule> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,
                                        LocalDate dateStart, LocalDate dateEnd) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Schedule> list = scheduleRepository.getAllBetweenDates(dateStart, dateEnd, pageable);

        return list;
    }

    public Page<Schedule> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.scheduleRepository.findAll(pageable);
    }

}
