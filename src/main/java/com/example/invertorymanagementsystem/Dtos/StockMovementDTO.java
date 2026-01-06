package com.example.invertorymanagementsystem.Dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class StockMovementDTO {
    private Integer id;
    private Integer productId;
    private Integer warehouseId;
    private Integer quantity;
    private String transactionDate;
    private String status;
    private LocalDateTime createdAt;
}
