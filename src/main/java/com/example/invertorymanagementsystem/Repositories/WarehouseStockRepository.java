package com.example.invertorymanagementsystem.Repositories;

import com.example.invertorymanagementsystem.Entities.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Integer> {

    // Crucial for the Service layer logic
    Optional<WarehouseStock> findByProductIdAndWarehouseId(Integer productId, Integer warehouseId);
}