package com.ahmedshaban.orderservice.service;


import com.ahmedshaban.orderservice.dto.OrderLineItemDto;
import com.ahmedshaban.orderservice.dto.OrderRequest;
import com.ahmedshaban.orderservice.model.Order;
import com.ahmedshaban.orderservice.model.OrderLineItem;
import com.ahmedshaban.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLine().stream().map(this::mapToOrderLine).collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);

    }

    private OrderLineItem mapToOrderLine(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSku(orderLineItem.getSku());
        orderLineItem.setQuantity(orderLineItem.getQuantity());
        orderLineItem.setPrice(orderLineItem.getPrice());
        return orderLineItem;
    }

}
