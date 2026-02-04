package com.example.invertorymanagementsystem.repositories;

import com.example.invertorymanagementsystem.entities.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Integer> {

    // Crucial for the Service layer logic
    Optional<WarehouseStock> findByProductIdAndWarehouseId(Integer productId, Integer warehouseId);
}