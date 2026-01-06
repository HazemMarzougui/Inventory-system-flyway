package com.example.invertorymanagementsystem.Dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class WarehouseDTO {
    private Integer id;
    private String name;
    private String location;
    private LocalDateTime createdAt;
}
