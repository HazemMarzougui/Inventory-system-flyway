package com.example.invertorymanagementsystem.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockMovementDTO {

    private Integer id;

    @NotNull(message = "Product ID is required")
    private Integer productId;

    @NotNull(message = "Warehouse ID is required")
    private Integer warehouseId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotBlank(message = "Status is required")
    private String status;

    private LocalDateTime createdAt;
}
