package com.spring.golub.controller;

import com.spring.golub.dto.UserLoginDTO;
import com.spring.golub.entity.User;
import com.spring.golub.entity.UserPrincipal;
import com.spring.golub.service.ScheduleService;
import com.spring.golub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String main() {
        return "welcome_page/welcome.html";
    }

    @RequestMapping("/home")
    public String mainPage(Authentication authentication) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        Optional.ofNullable(authentication)
                .ifPresent(auth -> {
                    UserPrincipal user = (UserPrincipal) auth.getPrincipal();
                    session.setAttribute("balance", user.getBalance().toString());
                });
        return "home_page/home.html";
    }
}
