package com.example.invertorymanagementsystem.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ProductDTO {
    private Integer id ;
    @NotBlank
    private String name ;
    @Positive
    private BigDecimal price ;
    private String category ;
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;
}
