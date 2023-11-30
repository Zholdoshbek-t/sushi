package com.inai.sushi.service;

import com.inai.sushi.dto.MakeOrderDto;
import com.inai.sushi.dto.OrdersDto;

public interface OrderService {

    void makeOrder(MakeOrderDto dto);
    OrdersDto getOrders();
    void deliverOrder(Long id);
    void deleteOrder(Long id);
    void cancelOrder(Long id);
}
