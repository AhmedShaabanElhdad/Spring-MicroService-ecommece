package com.ahmedshaban.inventoryservice.service;

import com.ahmedshaban.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public boolean checkIsInStock(String sku){
        return inventoryRepository.findBySku(sku).isPresent();
    }

}
