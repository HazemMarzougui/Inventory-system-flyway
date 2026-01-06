package com.example.invertorymanagementsystem.Controllers;
import com.example.invertorymanagementsystem.Entities.WarehouseStock;
import com.example.invertorymanagementsystem.Repositories.WarehouseStockRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final WarehouseStockRepository repository; // Or a specific InventoryService

    public InventoryController(WarehouseStockRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/stock-levels")
    public List<WarehouseStock> getLevels() {
        return repository.findAll();
    }
}