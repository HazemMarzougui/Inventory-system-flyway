package com.example.invertorymanagementsystem.Services;

import com.example.invertorymanagementsystem.Entities.StockMovement;
import com.example.invertorymanagementsystem.Repositories.StockMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    public List<StockMovement> getAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    public Optional<StockMovement> getStockMovementById(Integer id) {
        return stockMovementRepository.findById(id);
    }

    public StockMovement createStockMovement(StockMovement stockMovement) {
        return stockMovementRepository.save(stockMovement);
    }

    public StockMovement updateStockMovement(Integer id, StockMovement updatedMovement) {
        return stockMovementRepository.findById(id).map(movement -> {
            movement.setProduct(updatedMovement.getProduct());
            movement.setWarehouse(updatedMovement.getWarehouse());
            movement.setQuantity(updatedMovement.getQuantity());
            movement.setStatus(updatedMovement.getStatus());
            movement.setTransactionDate(updatedMovement.getTransactionDate());
            return stockMovementRepository.save(movement);
        }).orElseThrow(() -> new RuntimeException("StockMovement not found"));
    }

    public void deleteStockMovement(Integer id) {
        stockMovementRepository.deleteById(id);
    }
}
