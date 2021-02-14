package com.spring.golub.controller;

import com.spring.golub.entity.Exposition;
import com.spring.golub.entity.Schedule;
import com.spring.golub.service.ExpositionService;
import com.spring.golub.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/expositions")
public class ExpositionController {
    private static final String EXPOSITIONS_PAGE = "exposition_page/expositions.html";
    @Autowired private ScheduleService scheduleService;
    @Autowired private ExpositionService expositionService;

    @GetMapping("/showNewExpositionForm")
    public String showNewExpositionForm(Model model) {
        // create model attribute to bind form data
        Exposition exposition = new Exposition();
        model.addAttribute("exposition", exposition);
        return "exposition_page/new_exposition.html";
    }

    @PostMapping("/saveExposition")
    public String saveExposition(@ModelAttribute("exposition") Exposition exposition) {
        // save schedule to database
        expositionService.saveExposition(exposition);
        return "redirect:/schedules";
    }

    @GetMapping("/expositions")
    public String getExpositionPage(Model model, @PageableDefault(size = 4) Pageable pageable) {
        Page<Schedule> expositionPage = scheduleService.getAllSchedules(pageable);
        Pageable currentPageable = expositionPage.getPageable();
        int currentPageNum = currentPageable.getPageNumber();
        //no local
        int prevPage = currentPageNum - 1;
        int nextPage = currentPageNum + 1;
        //need builder for model
        model.addAttribute("expositions", expositionPage.getContent()).addAttribute("currentPage", currentPageNum + 1)
                .addAttribute("limit", currentPageable.getPageSize()).addAttribute("prevPage", prevPage)
                .addAttribute("nextPage", nextPage).addAttribute("totalPages", expositionPage.getTotalPages())
                .addAttribute("hasPrev", expositionPage.hasPrevious()).addAttribute("hasNext", expositionPage.hasNext());
        return EXPOSITIONS_PAGE;
    }

}
