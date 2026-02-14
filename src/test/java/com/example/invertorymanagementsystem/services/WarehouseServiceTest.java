package com.example.invertorymanagementsystem.services;

import com.example.invertorymanagementsystem.dtos.WarehouseDTO;
import com.example.invertorymanagementsystem.entities.Warehouse;
import com.example.invertorymanagementsystem.repositories.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class WarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseService warehouseService;


    @Test
    void createWarehouse_shoudReturnDTO()
    {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setName("Main Warehouse");
        dto.setLocation("City Center");

        Warehouse saved = new Warehouse();
          saved.setId(1);
          saved.setName("Main Warehouse");
          saved.setLocation("City Center");


        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(saved);

        WarehouseDTO result = warehouseService.createWarehouse(dto);

        assertEquals(1, result.getId());
        assertEquals("Main Warehouse" , result.getName());
    }

    @Test
    void getWarehouseById_notFound_shouldThrow() {
        when(warehouseRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> warehouseService.getWarehouseById(1));
    }
}
