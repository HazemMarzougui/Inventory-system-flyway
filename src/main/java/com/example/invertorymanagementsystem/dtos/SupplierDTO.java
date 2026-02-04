package com.example.invertorymanagementsystem.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupplierDTO {
    private Integer id;
    private String name;
    private String contact;
    private LocalDateTime createdAt;
}
