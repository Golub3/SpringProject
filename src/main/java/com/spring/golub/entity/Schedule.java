package com.spring.golub.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time_start", nullable = false)
    private LocalTime time_start;

    @Column(name = "time_end", nullable = false)
    private LocalTime time_end;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_h")
    private Hall hall;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_e")
    private Exposition exposition;
}
