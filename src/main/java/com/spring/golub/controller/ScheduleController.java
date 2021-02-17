package com.spring.golub.controller;

import com.spring.golub.dto.DateDTO;
import com.spring.golub.entity.Schedule;
import com.spring.golub.entity.UserPrincipal;
import com.spring.golub.service.ExpositionService;
import com.spring.golub.service.HallService;
import com.spring.golub.service.ScheduleService;
import com.spring.golub.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Controller
@SessionAttributes(value = {"sortField", "sortDir", "dates"})
@RequestMapping("/schedules")
public class ScheduleController {
    private static final String EXPOSITIONS_PAGE = "exposition_page/schedules.html";
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ExpositionService expositionService;
    @Autowired
    private HallService hallService;
    @Autowired
    private TicketService ticketService;

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

    @GetMapping("/page/{pageNo}/buyTicket")
    public String buyTicket(Authentication authentication, HttpSession session,
                            @RequestParam(value = "id", required = false) String id) {
        Optional.ofNullable(authentication)
                .ifPresent(auth -> {
                    UserPrincipal user = (UserPrincipal) auth.getPrincipal();
                    session.setAttribute("balance",
                            ticketService.ticketBuy(user.getUsername(),
                            Long.parseLong(id)).toString());
                });
        return "redirect:/schedules/page/{pageNo}";
    }

    @PostMapping("/saveSchedule")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        // save schedule to database
        scheduleService.saveSchedule(schedule);
        return "redirect:/schedules";
    }

    @RequestMapping(value = "/delete_schedule/{id}", method = RequestMethod.GET)
    public String handleDeleteSchedule(@PathVariable String id) {
        return "redirect:/schedules";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam(value = "sortField", required = false) String sortField,
                                @RequestParam(value = "sortDir", required = false) String sortDir,
                                @ModelAttribute("dates") @Valid DateDTO dates,
                                Model model) {

        if (sortField != null) {
            model.addAttribute("sortField", sortField).addAttribute("sortDir", sortDir);
        } else {
            sortField = (String) model.getAttribute("sortField");
            sortDir = (String) model.getAttribute("sortDir");
        }

        Page<Schedule> page = scheduleService.findPaginated(pageNo, 5, sortField, sortDir,
                LocalDate.parse(dates.getDateStart()), LocalDate.parse(dates.getDateEnd()));

        model.addAttribute("currentPage", pageNo).addAttribute("totalPages", page.getTotalPages())
                .addAttribute("totalItems", page.getTotalElements()).addAttribute("prevPage", pageNo - 1)
                .addAttribute("nextPage", pageNo + 1).addAttribute("sortField", sortField)
                .addAttribute("sortDir", sortDir).addAttribute("hasPrev", page.hasPrevious())
                .addAttribute("hasNext", page.hasNext()).addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc")
                .addAttribute("dates", dates).addAttribute("dateStart", dates.getDateStart())
                .addAttribute("dateEnd", dates.getDateEnd()).addAttribute("listSchedules", page.getContent());
        return "exposition_page/schedules.html";
    }

}