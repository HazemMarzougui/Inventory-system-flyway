package com.example.invertorymanagementsystem.Services;

import com.example.invertorymanagementsystem.Dtos.StockMovementDTO;
import com.example.invertorymanagementsystem.Entities.*;
import com.example.invertorymanagementsystem.Repositories.ProductRepository;
import com.example.invertorymanagementsystem.Repositories.StockMovementRepository;
import com.example.invertorymanagementsystem.Repositories.WarehouseRepository;
import com.example.invertorymanagementsystem.Repositories.WarehouseStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final WarehouseStockRepository warehouseStockRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockMovementService(StockMovementRepository smr,
                                WarehouseStockRepository wsr,
                                ProductRepository pr,
                                WarehouseRepository wr) {
        this.stockMovementRepository = smr;
        this.warehouseStockRepository = wsr;
        this.productRepository = pr;
        this.warehouseRepository = wr;
    }

    /**
     * PUBLIC ENTRY POINT
     * - Transactional
     * - Cache Eviction
     * - Business Logic
     */
    @CacheEvict(
            value = "warehouseStock",
            key = "'stock:' + #dto.productId + ':' + #dto.warehouseId"
    )
    public StockMovementDTO createMovement(StockMovementDTO dto) {
        log.info("🔴 CACHE EVICT: warehouseStock - stock:{}:{}",
                dto.getProductId(), dto.getWarehouseId());

        // 1. Fetch entities
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        // 2. Validate stock
        if ("OUT".equalsIgnoreCase(dto.getStatus())) {
            validateStockAvailability(
                    dto.getProductId(),
                    dto.getWarehouseId(),
                    dto.getQuantity()
            );
        }

        // 3. Save movement history
        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setWarehouse(warehouse);
        movement.setQuantity(dto.getQuantity());
        movement.setStatus(Stockstatus.valueOf(dto.getStatus().toUpperCase()));
        stockMovementRepository.save(movement);

        // 4. Update snapshot table
        updateWarehouseStock(dto);

        log.info("✅ Stock movement created: {} {} units for Product {} in Warehouse {}",
                dto.getStatus(), dto.getQuantity(), dto.getProductId(), dto.getWarehouseId());

        return dto;
    }

    /* ---------- INTERNAL HELPERS ---------- */

    private void validateStockAvailability(Integer productId,
                                           Integer warehouseId,
                                           Integer requestedQty) {

        WarehouseStock stock = warehouseStockRepository
                .findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new RuntimeException("No stock record found"));

        if (stock.getAvailableQuantity() < requestedQty) {
            throw new RuntimeException(
                    "Insufficient stock! Available: " + stock.getAvailableQuantity()
            );
        }
    }

    private void updateWarehouseStock(StockMovementDTO dto) {
        WarehouseStock stock = warehouseStockRepository
                .findByProductIdAndWarehouseId(dto.getProductId(), dto.getWarehouseId())
                .orElse(new WarehouseStock(
                        dto.getProductId(),
                        dto.getWarehouseId(),
                        0
                ));

        if ("IN".equalsIgnoreCase(dto.getStatus())) {
            stock.setAvailableQuantity(
                    stock.getAvailableQuantity() + dto.getQuantity()
            );
        } else {
            stock.setAvailableQuantity(
                    stock.getAvailableQuantity() - dto.getQuantity()
            );
        }

        warehouseStockRepository.save(stock);
    }

    /* ---------- READ PATH (CACHED) ---------- */

    @Cacheable(
            value = "warehouseStock",
            key = "'stock:' + #productId + ':' + #warehouseId"
    )
    public Integer getStock(Integer productId, Integer warehouseId) {
        log.info("🔵 CACHE MISS - Fetching from DB: stock:{}:{}", productId, warehouseId);

        Integer quantity = warehouseStockRepository
                .findByProductIdAndWarehouseId(productId, warehouseId)
                .map(WarehouseStock::getAvailableQuantity)
                .orElse(0);

        log.info("📊 Retrieved quantity from DB: {}", quantity);
        return quantity;
    }
}
