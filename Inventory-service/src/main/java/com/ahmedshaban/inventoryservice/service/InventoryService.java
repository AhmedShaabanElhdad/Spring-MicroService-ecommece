package com.ahmedshaban.inventoryservice.service;

import com.ahmedshaban.inventoryservice.model.Inventory;
import com.ahmedshaban.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public List<Inventory> checkIsInStock(List<String> sku){
        return inventoryRepository.findBySkuIn(sku);
    }

}
