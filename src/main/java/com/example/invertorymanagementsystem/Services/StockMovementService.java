package com.example.invertorymanagementsystem.Services;

import com.example.invertorymanagementsystem.Dtos.StockMovementDTO;
import com.example.invertorymanagementsystem.Entities.*;
import com.example.invertorymanagementsystem.Repositories.ProductRepository;
import com.example.invertorymanagementsystem.Repositories.StockMovementRepository;
import com.example.invertorymanagementsystem.Repositories.WarehouseRepository;
import com.example.invertorymanagementsystem.Repositories.WarehouseStockRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final WarehouseStockRepository warehouseStockRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockMovementService(StockMovementRepository smr, WarehouseStockRepository wsr,
                                ProductRepository pr, WarehouseRepository wr) {
        this.stockMovementRepository = smr;
        this.warehouseStockRepository = wsr;
        this.productRepository = pr;
        this.warehouseRepository = wr;
    }

    public StockMovementDTO createMovement(StockMovementDTO dto) {
        // 1. Fetch Entities
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        // 2. Validate Stock for 'OUT' movements
        if ("OUT".equalsIgnoreCase(dto.getStatus())) {
            validateStockAvailability(dto.getProductId(), dto.getWarehouseId(), dto.getQuantity());
        }

        // 3. Save Movement (History)
        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setWarehouse(warehouse);
        movement.setQuantity(dto.getQuantity());
        movement.setStatus(Stockstatus.valueOf(dto.getStatus().toUpperCase()));
        stockMovementRepository.save(movement);

        // 4. Update Snapshot (Performance Table)
        updateWarehouseStock(dto);

        return dto;
    }

    private void validateStockAvailability(Integer productId, Integer warehouseId, Integer requestedQty) {
        WarehouseStock stock = warehouseStockRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new RuntimeException("No stock record found for this product in this warehouse"));

        if (stock.getAvailableQuantity() < requestedQty) {
            throw new RuntimeException("Insufficient stock! Available: " + stock.getAvailableQuantity());
        }
    }

    private void updateWarehouseStock(StockMovementDTO dto) {
        WarehouseStock stock = warehouseStockRepository
                .findByProductIdAndWarehouseId(dto.getProductId(), dto.getWarehouseId())
                .orElse(new WarehouseStock(dto.getProductId(), dto.getWarehouseId(), 0));

        if ("IN".equalsIgnoreCase(dto.getStatus())) {
            stock.setAvailableQuantity(stock.getAvailableQuantity() + dto.getQuantity());
        } else if ("OUT".equalsIgnoreCase(dto.getStatus())) {
            stock.setAvailableQuantity(stock.getAvailableQuantity() - dto.getQuantity());
        }

        warehouseStockRepository.save(stock);
    }
}