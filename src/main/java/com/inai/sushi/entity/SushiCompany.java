package com.inai.sushi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`sushi_company`")
public class SushiCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String address;

    private String companyNumber;

    @ManyToOne
    @JoinColumn(
            name = "director_id",
            referencedColumnName = "id"
    )
    private AppUser director;
}
