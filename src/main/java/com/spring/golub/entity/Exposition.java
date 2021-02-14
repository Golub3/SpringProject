package com.spring.golub.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "exposition")
public class Exposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exp", nullable = false)
    private Long id_exp;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @OneToOne()
    private Ticket ticket;

    @OneToMany(mappedBy = "exposition", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Schedule> daySchedule;
}
