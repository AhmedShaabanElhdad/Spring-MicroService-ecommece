package com.ahmedshaban.inventoryservice.controller;

import com.ahmedshaban.inventoryservice.dto.InventoryResponse;
import com.ahmedshaban.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {

    InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> sku) {
        return inventoryService
                .checkIsInStock(sku)
                .stream()
                .map(inventory -> InventoryResponse
                        .builder()
                        .isInStock(inventory.getQuality() > 0)
                        .sku(inventory.getSku())
                        .build())
                .collect(Collectors.toList());
    }


}
