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

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "exposition")
    private Ticket ticket;

    @OneToMany(mappedBy = "exposition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Schedule> daySchedule;
}
