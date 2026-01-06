package com.example.invertorymanagementsystem.Services;

import com.example.invertorymanagementsystem.Dtos.WarehouseDTO;
import com.example.invertorymanagementsystem.Entities.Warehouse;
import com.example.invertorymanagementsystem.Repositories.WarehouseRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public WarehouseDTO createWarehouse(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        Warehouse saved = warehouseRepository.save(warehouse);
        return convertToDTO(saved);
    }

    private WarehouseDTO convertToDTO(Warehouse warehouse) {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setId(warehouse.getId());
        dto.setName(warehouse.getName());
        dto.setLocation(warehouse.getLocation());
        dto.setCreatedAt(warehouse.getCreatedAt());
        return dto;
    }
}