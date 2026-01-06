package com.example.invertorymanagementsystem.Controllers;

import com.example.invertorymanagementsystem.Dtos.SupplierDTO;
import com.example.invertorymanagementsystem.Services.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<SupplierDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierDTO getSupplier(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public SupplierDTO createSupplier(@RequestBody SupplierDTO dto) {
        return supplierService.createSupplier(dto);
    }

    @PutMapping("/{id}")
    public SupplierDTO updateSupplier(
            @PathVariable Integer id,
            @RequestBody SupplierDTO dto) {
        return supplierService.updateSupplier(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
    }
}

