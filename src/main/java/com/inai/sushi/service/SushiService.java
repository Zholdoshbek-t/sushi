package com.inai.sushi.service;

import com.inai.sushi.dto.SushiDetail;
import com.inai.sushi.dto.SushiMenu;
import com.inai.sushi.entity.Sushi;

import java.util.List;

public interface SushiService {

    List<SushiMenu> getSushis();
    SushiDetail getSushi(Long id);
    Sushi createSushi(Sushi sushi);
}
