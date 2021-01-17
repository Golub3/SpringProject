package com.spring.golub.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.golub.entity.User;
import com.spring.golub.service.UserService;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {
    private static final String USERS_PAGE = "user_page/users.html";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsersPage(Model model,
                               @PageableDefault(sort = {"id"}, size = 2) Pageable pageable) {
        Page<User> userPage = userService.getAllUsers(pageable);
        Pageable currentPageable = userPage.getPageable();
        int currentPageNum = currentPageable.getPageNumber();
        int prevPage = currentPageNum - 1;
        int nextPage = currentPageNum + 1;

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", currentPageNum + 1);
        model.addAttribute("limit", currentPageable.getPageSize());
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("hasPrev", userPage.hasPrevious());
        model.addAttribute("hasNext", userPage.hasNext());

        return USERS_PAGE;
    }
}
