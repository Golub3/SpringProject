package com.spring.golub.controller;

import com.spring.golub.dto.DateDTO;
import com.spring.golub.dto.UserLoginDTO;
import com.spring.golub.entity.Schedule;
import com.spring.golub.service.ExpositionService;
import com.spring.golub.service.HallService;
import com.spring.golub.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    private static final String EXPOSITIONS_PAGE = "exposition_page/schedules.html";
    @Autowired private ScheduleService scheduleService;
    @Autowired private ExpositionService expositionService;
    @Autowired private HallService hallService;
    private DateDTO datesTemp = new DateDTO("2021-01-01", "2021-05-01");

    @GetMapping()
    public String viewHomePage(Model model) {
        return findPaginated(1, "exposition.theme", "asc",
                new DateDTO("2021-01-01", "2021-05-01"), model);
    }

    @GetMapping("/showNewScheduleForm")
    public String showNewScheduleForm(Model model) {
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

    @RequestMapping(value = "/delete_schedule/{id}", method = RequestMethod.GET)
    public String handleDeleteSchedule(@PathVariable String id) {
//        System.out.println(id);
//        System.out.println("test");
        return "redirect:/schedules";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                @ModelAttribute("dates") @Valid DateDTO dates,
                                Model model) {

        int pageSize = 5;
        if (dates.getDateStart() == null){
            dates = datesTemp;
        } else datesTemp = dates;
        log.info(dates.toString());

        Page<Schedule> page = scheduleService.findPaginated(pageNo, pageSize, sortField, sortDir,
                LocalDate.parse(dates.getDateStart()), LocalDate.parse(dates.getDateEnd()));
        log.info(page.toString());
//        DateDTO dates = new DateDTO(LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
        //BUILDER ///ADD DATA TO DTO
        model.addAttribute("currentPage", pageNo).addAttribute("totalPages", page.getTotalPages())
                .addAttribute("totalItems", page.getTotalElements()).addAttribute("prevPage", pageNo - 1)
                .addAttribute("nextPage", pageNo + 1).addAttribute("sortField", sortField)
                .addAttribute("sortDir", sortDir).addAttribute("hasPrev", page.hasPrevious())
                .addAttribute("hasNext", page.hasNext()).addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc")
                .addAttribute("dates", dates).addAttribute("dateStart", dates.getDateStart())
                .addAttribute("dateEnd", dates.getDateEnd()).addAttribute("listSchedules", page.getContent());
        return "exposition_page/schedules.html";
    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//        Page<Schedule> page = scheduleService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        DateDTO dates = new DateDTO(LocalDate.parse("2021-01-01"), LocalDate.parse("2030-01-01"));
//        model.addAttribute("currentPage", pageNo).addAttribute("totalPages", page.getTotalPages())
//                .addAttribute("totalItems", page.getTotalElements()).addAttribute("prevPage", pageNo - 1)
//                .addAttribute("nextPage", pageNo + 1).addAttribute("sortField", sortField)
//                .addAttribute("sortDir", sortDir).addAttribute("hasPrev", page.hasPrevious())
//                .addAttribute("hasNext", page.hasNext())
//                .addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc").addAttribute("dates", dates)
//                .addAttribute("dateStart", dates.getDateStart()).addAttribute("dateEnd", dates.getDateEnd());
//
//        model.addAttribute("listSchedules", page.getContent());
//        return "exposition_page/schedules.html";
//    }

}
