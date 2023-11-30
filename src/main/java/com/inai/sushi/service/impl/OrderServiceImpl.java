package com.inai.sushi.service.impl;

import com.inai.sushi.dto.MakeOrderDto;
import com.inai.sushi.dto.OrderDto;
import com.inai.sushi.dto.OrdersDto;
import com.inai.sushi.entity.Order;
import com.inai.sushi.entity.Sushi;
import com.inai.sushi.repository.OrderRepository;
import com.inai.sushi.repository.SushiRepository;
import com.inai.sushi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SushiRepository sushiRepository;

    @Override
    public void makeOrder(MakeOrderDto dto) {
        Optional<Sushi> sushi = sushiRepository.findById(Long.parseLong(dto.getSushiId()));

        if (sushi.isPresent()) {
            Order order = Order.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .number(dto.getNumber())
                    .address(dto.getAddress())
                    .comments(dto.getComments())
                    .payingSum(dto.getPayingSum())
                    .sushi(sushi.get())
                    .delivered(false)
                    .date(LocalDate.now())
                    .price(sushi.get().getPrice())
                    .build();

            orderRepository.save(order);
        }
    }

    @Override
    public OrdersDto getOrders() {
        OrdersDto dto = new OrdersDto();

        orderRepository.findAll().forEach(
                order -> {
                    if (!order.isDelivered()) {
                        dto.getOrders().add(mapToOrderDto(order));
                    } else {
                        dto.getDeliveredOrders().add(mapToOrderDto(order));
                    }
                }
        );

        return dto;
    }

    @Override
    public void deliverOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        order.ifPresent(value -> {
            value.setDelivered(true);
            orderRepository.save(value);
        });
    }

    @Override
    public void deleteOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        order.ifPresent(orderRepository::delete);
    }

    @Override
    public void cancelOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        order.ifPresent(value -> {
            value.setDelivered(false);
            orderRepository.save(value);
        });
    }

    private OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .firstName(order.getFirstName())
                .lastName(order.getLastName())
                .number(order.getNumber())
                .date(String.format("%d - %d - %d", order.getDate().getDayOfMonth(),
                        order.getDate().getMonthValue(),
                        order.getDate().getYear()))
                .payingSum(order.getPayingSum())
                .price(order.getPrice())
                .address(order.getAddress())
                .comments(order.getComments())
                .sushiName(order.getSushi().getName())
                .delivered(order.isDelivered())
                .build();
    }
}
