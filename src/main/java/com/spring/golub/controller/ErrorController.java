package com.spring.golub.controller;

import com.spring.golub.dto.UserRegistrationDTO;
import com.spring.golub.exceptions.IllegalEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

//@Slf4j
//@ControllerAdvice
//public class ErrorController {
//    private static final String ERROR_PAGE = "errors/error.html";
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception ex) {
//        return ERROR_PAGE;
//    }
//
//    @ExceptionHandler(Throwable.class)
//    public String handleAnyException(Throwable ex, HttpServletRequest request) {
//        return ERROR_PAGE;
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public String handleIllegalArgumentException(IllegalArgumentException ex) {
//        return ERROR_PAGE;
//    }
//
//    @ExceptionHandler(IllegalEmailException.class)
//    public String handleIllegalEmailException(IllegalEmailException ex,
//                                              Model model) {
//        model.addAttribute("error", "email");
//        model.addAttribute("user", new UserRegistrationDTO());
//        return "registration_page/registration_form.html";
//    }
//
//}
