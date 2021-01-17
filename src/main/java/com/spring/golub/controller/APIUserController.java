package com.spring.golub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.golub.dto.UsersDTO;
import com.spring.golub.service.UserService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/users")
public class APIUserController {
    private UserService userService;

    @Autowired
    public APIUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public UsersDTO getAllUsers(@RequestParam(name = "limit") Optional<Integer> limit,
                                @RequestParam(name = "page") Optional<Integer> page) {
        return null;
    }
}
