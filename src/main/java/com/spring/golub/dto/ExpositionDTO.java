package com.spring.golub.dto;

import com.spring.golub.entity.Exposition;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ExpositionDTO {
    private List<Exposition> expositions;
    private Integer totalPages;
}
