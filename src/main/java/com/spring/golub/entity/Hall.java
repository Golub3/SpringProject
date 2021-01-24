package com.spring.golub.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "hall",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hall", nullable = false)
    private Long id_hall;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Schedule> daySchedule;
}
