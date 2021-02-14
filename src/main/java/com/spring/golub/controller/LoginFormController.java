package com.spring.golub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.spring.golub.dto.UserLoginDTO;
import com.spring.golub.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginFormController {
    public static final String LOGIN_FORM = "login_page/login_form.html";
    private final UserService userService;

    @Autowired
    public LoginFormController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public String loginFormController(@ModelAttribute("user") @Valid UserLoginDTO user,
                                      BindingResult bindingResult,
                                      Model model) {
        log.info("{}", userService.findByUserLogin(user));
        log.info("{}", user);

        if (!bindingResult.hasErrors()) {
            if (!userService.findByUserLogin(user).isPresent()) {
                log.error("jpa error");
                model.addAttribute("error", "credentials");
                return LOGIN_FORM;
            }
            log.info("successful login");
            return "redirect:/schedules/";
        } else {
            log.error("binding res error");
            model.addAttribute("error", "validation");
            return LOGIN_FORM;
        }
    }

    @GetMapping()
    public String getLoginPage(@ModelAttribute("user") UserLoginDTO user, Principal principal) {
        if (principal != null) return "redirect:/schedules/";
        return LOGIN_FORM;
    }


}
