package com.inai.sushi.config;

import com.inai.sushi.entity.Order;
import com.inai.sushi.entity.Sushi;
import com.inai.sushi.repository.OrderRepository;
import com.inai.sushi.repository.SushiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class DBInput {

    private final SushiRepository sushiRepository;
    private final OrderRepository orderRepository;

    @Bean
    @Transactional
    public void insert() {
        String directory = "/images/sushi_images/";
        String[] names = {"Большие роллы", "Бонито", "Дракон", "Дракон-премиум",
                "Фила спешиал", "Калифорния", "Канада", "Таваки-кавата"};
        String[] peopleNames = {"Tilek", "Asan", "Uson", "Alex", "Azamat", "Bermet", "Asel", "Samat"};
        int[] prices = {300, 499, 299, 500, 799, 699, 399, 400};
        String ing = "Salmon, Avocado, Wasabi, Rice, Seaweed, Soy Sauce, Ginger, Tuna, Sesame, Cucumber";
        List<Sushi> sushis = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            Optional<Sushi> optionalSushi = sushiRepository.findByName(names[i - 1]);

            if (optionalSushi.isEmpty()) {
                Sushi sushi = Sushi.builder()
                        .name(names[i - 1])
                        .price(prices[i - 1])
                        .ingredients(ing)
                        .cookingTime(20)
                        .imagePath(directory + i + ".png")
                        .build();

                sushiRepository.save(sushi);

                sushis.add(sushi);
            }
        }

        for (int i = 0; i < 8; i++) {
            Order order = Order.builder()
                    .firstName(peopleNames[i]).lastName(peopleNames[i]).date(LocalDate.now())
                    .delivered(false).address("Lev Tolstoy 22").comments("Call me when you will come")
                    .payingSum(1000).price(prices[i]).sushi(sushis.get(i)).number("+996777777777")
                    .build();

            orderRepository.save(order);
        }
    }
}
