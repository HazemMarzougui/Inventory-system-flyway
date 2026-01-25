package com.example.invertorymanagementsystem.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "warehouse_id", nullable = false)
    private Integer warehouseId;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity = 0;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Version
    @Column(nullable = false)
    private Integer version;

    // Helper constructor for the Service layer
    public WarehouseStock(Integer productId, Integer warehouseId, Integer availableQuantity) {
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.availableQuantity = availableQuantity;
        this.lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    @PrePersist
    public void updateTimestamp() {
        this.lastUpdated = LocalDateTime.now();
    }
}