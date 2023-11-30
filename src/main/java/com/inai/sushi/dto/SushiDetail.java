package com.inai.sushi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SushiDetail {

    private Long id;

    private String name;

    private String ingredients;

    private int price;

    private int cookingTime;

    private String imagePath;
}
