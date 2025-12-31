package com.example.invertorymanagementsystem.Services;

import com.example.invertorymanagementsystem.Entities.Warehouse;
import com.example.invertorymanagementsystem.Repositories.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Integer id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Integer id, Warehouse updatedWarehouse) {
        return warehouseRepository.findById(id).map(warehouse -> {
            warehouse.setName(updatedWarehouse.getName());
            warehouse.setLocation(updatedWarehouse.getLocation());
            return warehouseRepository.save(warehouse);
        }).orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    public void deleteWarehouse(Integer id) {
        warehouseRepository.deleteById(id);
    }
}
