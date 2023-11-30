package com.inai.sushi.controller;

import com.inai.sushi.dto.MakeOrderDto;
import com.inai.sushi.dto.SushiDetail;
import com.inai.sushi.dto.SushiMenu;
import com.inai.sushi.service.OrderService;
import com.inai.sushi.service.SushiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sushi")
@RequiredArgsConstructor
public class SushiController {

    private final SushiService sushiService;
    private final OrderService orderService;

    @GetMapping("/menu")
    public String getSushiMenu(Model model) {
        List<SushiMenu> sushiMenuList = sushiService.getSushis();
        model.addAttribute("sushiMenuList", sushiMenuList);
        return "sushiMenu";
    }

    @GetMapping("/details/{id}")
    public String getSushiDetails(@PathVariable Long id, Model model) {
        SushiDetail sushiDetail = sushiService.getSushi(id);

        model.addAttribute("makeOrderDto", new MakeOrderDto());
        model.addAttribute("sushiDetail", sushiDetail);

        return "sushiDetails";
    }

    @PostMapping("/order")
    public String placeOrder(@ModelAttribute MakeOrderDto makeOrderDto) {
        orderService.makeOrder(makeOrderDto);
        return "redirect:/sushi/menu";
    }
}

