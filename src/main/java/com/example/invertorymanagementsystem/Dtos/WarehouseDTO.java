package com.example.invertorymanagementsystem.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WarehouseDTO {

    private Integer id;

    @NotBlank(message = "Warehouse name is required")
    private String name;

    @NotBlank(message = "Warehouse location is required")
    private String location;

    private LocalDateTime createdAt;
}
