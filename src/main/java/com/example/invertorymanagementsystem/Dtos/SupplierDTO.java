package com.example.invertorymanagementsystem.Dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class SupplierDTO {
    private Integer id;
    private String name;
    private String contact;
    private LocalDateTime createdAt;
}
