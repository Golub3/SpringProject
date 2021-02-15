package com.spring.golub.controller;

import com.spring.golub.dto.UserLoginDTO;
import com.spring.golub.entity.Schedule;
import com.spring.golub.entity.Ticket;
import com.spring.golub.entity.User;
import com.spring.golub.entity.UserPrincipal;
import com.spring.golub.service.ScheduleService;
import com.spring.golub.service.TicketService;
import com.spring.golub.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    @RequestMapping("/")
    public String main() {
        return "welcome_page/welcome.html";
    }

    @RequestMapping("/home")
    public String mainPage(Authentication authentication, HttpSession session,
                           Model model, @PageableDefault(size = 5) Pageable pageable) {
        Optional.ofNullable(authentication)
                .ifPresent(auth -> {
                    UserPrincipal user = (UserPrincipal) auth.getPrincipal();
                    if(session.getAttribute("balance") == null) {
                        session.setAttribute("balance", user.getBalance().toString());
                    }
                   model.addAttribute("tickets", ticketService.getAllTickets(user.getUserId()));
                });

        return "home_page/home.html";
    }
}
