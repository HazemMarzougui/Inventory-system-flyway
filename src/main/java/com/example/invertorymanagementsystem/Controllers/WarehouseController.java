package com.example.invertorymanagementsystem.Controllers;
import com.example.invertorymanagementsystem.Dtos.WarehouseDTO;
import com.example.invertorymanagementsystem.Services.WarehouseService;
import jakarta.validation.Valid;
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
    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseDTO getWarehouse(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping
    public WarehouseDTO createWarehouse(@Valid  @RequestBody WarehouseDTO dto) {
        return warehouseService.createWarehouse(dto);
    }

    @PutMapping("/{id}")
    public WarehouseDTO updateWarehouse(@Valid
            @PathVariable Integer id,
            @RequestBody WarehouseDTO dto) {
        return warehouseService.updateWarehouse(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteWarehouse(@PathVariable Integer id) {
        warehouseService.deleteWarehouse(id);
    }
}
