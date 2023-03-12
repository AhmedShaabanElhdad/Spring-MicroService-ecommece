package com.ahmedshaban.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {

    private String id;
    private String sku;
    private BigDecimal price;
    private Integer quantity;
}
