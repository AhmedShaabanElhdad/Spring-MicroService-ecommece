package com.ahmedshaban.orderservice.service;


import com.ahmedshaban.orderservice.dto.InventoryResponse;
import com.ahmedshaban.orderservice.dto.OrderLineItemDto;
import com.ahmedshaban.orderservice.dto.OrderRequest;
import com.ahmedshaban.orderservice.model.Order;
import com.ahmedshaban.orderservice.model.OrderLineItem;
import com.ahmedshaban.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        // get all Order line
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLine().stream().map(this::mapToOrderLine).collect(Collectors.toList());
        order.setOrderLineItems(orderLineItems);

        // get all sku from order line
        List<String> skuList = order.getOrderLineItems()
                .stream()
                .map(OrderLineItem::getSku)
                .collect(Collectors.toList());

        //check quantity for item in stock using inventory service
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("sku", skuList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean isInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);

        if (isInStock)
            orderRepository.save(order);
        else
            throw new IllegalArgumentException("product in not in stock");

    }

    private OrderLineItem mapToOrderLine(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSku(orderLineItem.getSku());
        orderLineItem.setQuantity(orderLineItem.getQuantity());
        orderLineItem.setPrice(orderLineItem.getPrice());
        return orderLineItem;
    }

}
