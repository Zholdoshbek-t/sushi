package com.inai.sushi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`rewiev`")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private int stars;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(
            name = "app_user",
            referencedColumnName = "id"
    )
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(
            name = "sushi",
            referencedColumnName = "id"
    )
    private Sushi sushi;

    @ManyToOne
    @JoinColumn(
            name = "sushi_company",
            referencedColumnName = "id"
    )
    private SushiCompany sushiCompany;
}
