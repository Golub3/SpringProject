package com.spring.golub.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class DateDTO {
    private String dateStart;
    private String dateEnd;
}
