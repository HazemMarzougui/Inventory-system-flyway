package com.example.invertorymanagementsystem;

import com.example.invertorymanagementsystem.Dtos.StockMovementDTO;
import com.example.invertorymanagementsystem.Entities.Product;
import com.example.invertorymanagementsystem.Entities.Warehouse;
import com.example.invertorymanagementsystem.Repositories.ProductRepository;
import com.example.invertorymanagementsystem.Repositories.WarehouseRepository;
import com.example.invertorymanagementsystem.Services.StockMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class WarehouseStockCacheTest extends BaseIntegrationTest {

    @Autowired
    private StockMovementService stockService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    private Integer testProductId;
    private Integer testWarehouseId;

    @BeforeEach
    void setupTestData() {
        // Create Product entity directly
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(new java.math.BigDecimal("99.99"));
        product.setCategory("Electronics");
        Product savedProduct = productRepository.save(product);
        testProductId = savedProduct.getId();

        // Create Warehouse entity directly
        Warehouse warehouse = new Warehouse();
        warehouse.setName("Test Warehouse");
        warehouse.setLocation("Test Location");
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        testWarehouseId = savedWarehouse.getId();
    }

    @Test
    void shouldCacheAndEvictWarehouseStockCorrectly() {

        // 1️⃣ Add stock (DB write → cache evicted)
        StockMovementDTO in = new StockMovementDTO();
        in.setProductId(testProductId);
        in.setWarehouseId(testWarehouseId);
        in.setQuantity(50);
        in.setStatus("IN");

        stockService.createMovement(in);

        // 2️⃣ First read → DB hit → cache MISS
        Integer firstRead = stockService.getStock(testProductId, testWarehouseId);
        assertThat(firstRead).isEqualTo(50);

        // 3️⃣ Second read → Redis cache HIT
        Integer secondRead = stockService.getStock(testProductId, testWarehouseId);
        assertThat(secondRead).isEqualTo(50);

        // 4️⃣ Remove stock → DB write → cache EVICT
        StockMovementDTO out = new StockMovementDTO();
        out.setProductId(testProductId);
        out.setWarehouseId(testWarehouseId);
        out.setQuantity(20);
        out.setStatus("OUT");

        stockService.createMovement(out);

        // 5️⃣ Read again → DB hit → cache refreshed
        Integer afterEvictRead = stockService.getStock(testProductId, testWarehouseId);
        assertThat(afterEvictRead).isEqualTo(30);
    }
}