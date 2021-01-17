package com.spring.golub.dto;

import lombok.*;
import com.spring.golub.entity.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDTO {
    private List<User> users;
    private Integer totalPages;
}
