package com.example.invertorymanagementsystem.services;

import com.example.invertorymanagementsystem.dtos.SupplierDTO;
import com.example.invertorymanagementsystem.entities.Supplier;
import com.example.invertorymanagementsystem.repositories.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Spring Import
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional(readOnly = true)
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SupplierDTO getSupplierById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));
        return convertToDTO(supplier);
    }

    public SupplierDTO createSupplier(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());

        Supplier saved = supplierRepository.save(supplier);
        return convertToDTO(saved);
    }

    public SupplierDTO updateSupplier(Integer id, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + id));

        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());

        return convertToDTO(supplierRepository.save(supplier));
    }

    public void deleteSupplier(Integer id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Supplier not found");
        }
        supplierRepository.deleteById(id);
    }

    private SupplierDTO convertToDTO(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setContact(supplier.getContact());
        dto.setCreatedAt(supplier.getCreatedAt());
        return dto;
    }
}
