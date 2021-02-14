package com.spring.golub.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Long ticket_id;

    @OneToOne(cascade =  CascadeType.ALL,
            mappedBy = "ticket")
    @JoinColumn(name = "id_exp")
    private Exposition exposition;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
}