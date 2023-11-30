package com.inai.sushi.service.impl;

import com.inai.sushi.dto.SushiDetail;
import com.inai.sushi.dto.SushiMenu;
import com.inai.sushi.entity.Sushi;
import com.inai.sushi.repository.SushiRepository;
import com.inai.sushi.service.SushiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SushiServiceImpl implements SushiService {

    private final SushiRepository sushiRepository;


    @Override
    public List<SushiMenu> getSushis() {
        return sushiRepository.findAll().stream()
                .map(sushi -> SushiMenu.builder()
                        .id(sushi.getId())
                        .name(sushi.getName())
                        .imagePath(sushi.getImagePath())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public SushiDetail getSushi(Long id) {
        Optional<Sushi> sushi = sushiRepository.findById(id);

        return sushi.map(value -> SushiDetail.builder()
                .id(value.getId())
                .name(value.getName())
                .price(value.getPrice())
                .ingredients(value.getIngredients())
                .imagePath(value.getImagePath())
                .cookingTime(value.getCookingTime())
                .build()).orElse(null);
    }

    @Override
    public Sushi createSushi(Sushi sushi) {
        return sushiRepository.save(sushi);
    }
}
