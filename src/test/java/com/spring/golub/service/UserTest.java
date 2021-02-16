package com.spring.golub.service;


import com.spring.golub.entity.RoleType;
import com.spring.golub.entity.User;
import com.spring.golub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUser_WhenFindById() {
        when(userRepository.findByEmail(any()))
                .thenReturn(getUser());

        User user = userService.findByUserLogin("l").orElseGet(User::new);
        Optional<User> actual = userRepository.findByEmail("l");

        assertEquals(user, actual.get());
    }

    @Test
    void shouldGetAllUsers() {
        //given
        int expectedNumber = 2;
        when(userRepository.findAll(PageRequest.of(0, 2)))
                .thenReturn(givenUsersPages());
        //when
        Page<User> usersPage = userService.getAllUsers(PageRequest.of(0, 2));
        //then
        assertEquals(usersPage.getContent().size(), expectedNumber);

        User user = usersPage.getContent().get(0);
        assertThat(user)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("firstName", "f")
                .hasFieldOrPropertyWithValue("lastName", "l")
                .hasFieldOrPropertyWithValue("role", RoleType.USER)
                .hasFieldOrPropertyWithValue("email", "e1");
    }

    Page<User> givenUsersPages() {
        return new PageImpl<>(
                Arrays.asList(
                User.builder()
                        .id(1L)
                        .firstName("f")
                        .lastName("l")
                        .password("p")
                        .email("e1")
                        .balance(new BigDecimal(0))
                        .role(RoleType.USER).build(),
                User.builder()
                        .id(2L)
                        .firstName("f")
                        .lastName("l")
                        .password("p")
                        .email("e1")
                        .balance(new BigDecimal(0))
                        .role(RoleType.USER).build()
                ),
                PageRequest.of(0, 2),
                1);
    }

    private Optional<User> getUser(){
        return Optional.of(User.builder()
                .id(1L)
                .firstName("f")
                .lastName("l")
                .password("p")
                .email("e1")
                .balance(new BigDecimal(0))
                .role(RoleType.USER).build());
    }
}
