package com.example.invertorymanagementsystem.Controllers;
import com.example.invertorymanagementsystem.Entities.Warehouse;
import com.example.invertorymanagementsystem.Services.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouse(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id).orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public Warehouse updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouse(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Integer id) {
        warehouseService.deleteWarehouse(id);
    }
}
