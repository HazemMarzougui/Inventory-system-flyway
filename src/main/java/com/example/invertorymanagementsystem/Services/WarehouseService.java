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

    public List<WarehouseDTO> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public WarehouseDTO getWarehouseById(Integer id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id " + id));
        return convertToDTO(warehouse);
    }

    public WarehouseDTO createWarehouse(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        return convertToDTO(warehouseRepository.save(warehouse));
    }

    public WarehouseDTO updateWarehouse(Integer id, WarehouseDTO dto) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id " + id));

        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        return convertToDTO(warehouseRepository.save(warehouse));
    }

    public void deleteWarehouse(Integer id) {
        if (!warehouseRepository.existsById(id)) {
            throw new RuntimeException("Warehouse not found with id " + id);
        }
        warehouseRepository.deleteById(id);
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
