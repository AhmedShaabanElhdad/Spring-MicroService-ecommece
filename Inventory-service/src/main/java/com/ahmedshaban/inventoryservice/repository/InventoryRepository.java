package com.ahmedshaban.inventoryservice.repository;

import com.ahmedshaban.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findBySku(String sku);
}
