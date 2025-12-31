package com.example.invertorymanagementsystem.Controllers;
import org.springframework.web.bind.annotation.*;
import com.example.invertorymanagementsystem.Entities.StockMovement;
import com.example.invertorymanagementsystem.Services.StockMovementService;
import java.util.List;
@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public List<StockMovement> getAllStockMovements() {
        return stockMovementService.getAllStockMovements();
    }

    @GetMapping("/{id}")
    public StockMovement getStockMovement(@PathVariable Integer id) {
        return stockMovementService.getStockMovementById(id).orElseThrow(() -> new RuntimeException("StockMovement not found"));
    }

    @PostMapping
    public StockMovement createStockMovement(@RequestBody StockMovement movement) {
        return stockMovementService.createStockMovement(movement);
    }

    @PutMapping("/{id}")
    public StockMovement updateStockMovement(@PathVariable Integer id, @RequestBody StockMovement movement) {
        return stockMovementService.updateStockMovement(id, movement);
    }

    @DeleteMapping("/{id}")
    public void deleteStockMovement(@PathVariable Integer id) {
        stockMovementService.deleteStockMovement(id);
    }
}
