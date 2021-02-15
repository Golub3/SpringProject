package com.spring.golub.controller;

import com.spring.golub.dto.UserRegistrationDTO;
import com.spring.golub.entity.UserPrincipal;
import com.spring.golub.exceptions.ForbiddenPageException;
import com.spring.golub.exceptions.IllegalEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@ControllerAdvice
@Controller
public class MyErrorController implements ErrorController {
    private static final String ERROR_PAGE = "errors/error.html";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return ERROR_PAGE;
    }

    @ExceptionHandler(Throwable.class)
    public String handleAnyException(Throwable ex, HttpServletRequest request) {
        return ERROR_PAGE;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ERROR_PAGE;
    }

    @ExceptionHandler(IllegalEmailException.class)
    public String handleIllegalEmailException(IllegalEmailException ex,
                                              Model model) {
        model.addAttribute("error", "email");
        model.addAttribute("user", new UserRegistrationDTO());
        return "registration_page/registration_form.html";
    }

    @ExceptionHandler(ForbiddenPageException.class)
    public String forbiddenPageException(ForbiddenPageException ex,
                                         Model model,
                                         Authentication authentication) {
        return processException(ex, model, authentication);
    }

    private String processException(Exception ex, Model model, Authentication authentication) {
        log.error(Arrays.toString(ex.getStackTrace()));
        log.error(ex.getMessage());
        model.addAttribute("exception", ex);
        Optional.ofNullable(authentication)
                .ifPresent(auth -> {
                    UserPrincipal user = (UserPrincipal) auth.getPrincipal();
                    model.addAttribute("userId", user.getUserId());
                });
        return ERROR_PAGE;
    }

    @Override
    public String getErrorPath() {
        return "redirect:/error";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return ERROR_PAGE;
    }
}
