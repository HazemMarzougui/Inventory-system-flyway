package com.example.invertorymanagementsystem.controllers;
import com.example.invertorymanagementsystem.dtos.StockMovementDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.invertorymanagementsystem.services.StockMovementService;
@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping
    public StockMovementDTO createMovement(@Valid @RequestBody StockMovementDTO dto) {
        return stockMovementService.createMovement(dto);
    }
}
