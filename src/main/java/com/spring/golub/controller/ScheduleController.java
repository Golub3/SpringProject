package com.spring.golub.controller;

import com.spring.golub.entity.Hall;
import com.spring.golub.entity.Schedule;
import com.spring.golub.service.ExpositionService;
import com.spring.golub.service.HallService;
import com.spring.golub.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    private static final String EXPOSITIONS_PAGE = "exposition_page/schedules.html";
    @Autowired private ScheduleService scheduleService;
    @Autowired private ExpositionService expositionService;
    @Autowired private HallService hallService;

    @GetMapping()
    public String viewHomePage(Model model) {
        return findPaginated(1, "exposition.theme", "asc", model);
    }

    @GetMapping("/showNewScheduleForm")
    public String showNewScheduleForm(Model model) {
        // create model attribute to bind form data
        Schedule schedule = new Schedule();
        model.addAttribute("schedule", schedule);
        model.addAttribute("halls", hallService.getAll());
        model.addAttribute("expositions", expositionService.getAll());
        return "exposition_page/new_schedule.html";
    }

    @PostMapping("/saveSchedule")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        // save schedule to database
        scheduleService.saveSchedule(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 4;

        Page<Schedule> page = scheduleService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Schedule> listSchedules = page.getContent();
        //no local
        int prevPage = pageNo - 1;
        int nextPage = pageNo + 1;
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("hasPrev", page.hasPrevious());
        model.addAttribute("hasNext", page.hasNext());
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listSchedules", listSchedules);
        return "exposition_page/schedules.html";
    }

}
