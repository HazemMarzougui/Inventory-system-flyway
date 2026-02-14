package com.example.invertorymanagementsystem.controllers;

import com.example.invertorymanagementsystem.security.JwtAuthenticationFilter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.example.invertorymanagementsystem.dtos.WarehouseDTO;
import com.example.invertorymanagementsystem.services.WarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = WarehouseController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class
        )
)class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WarehouseService warehouseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getWarehouse_shouldReturnWarehouse() throws Exception {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setId(1);
        dto.setName("Main Warehouse");
        dto.setLocation("City Center");

        when(warehouseService.getWarehouseById(1)).thenReturn(dto);

        mockMvc.perform(get("/api/warehouses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Main Warehouse"));
    }

    @Test
    void getAllWarehouses_shouldReturnList() throws Exception {

        WarehouseDTO dto = new WarehouseDTO();
        dto.setId(1);
        dto.setName("Main Warehouse");

        when(warehouseService.getAllWarehouses())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/warehouses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Main Warehouse"));
    }

    // UPDATE
    @Test
    void updateWarehouse_shouldReturnUpdated() throws Exception {

        WarehouseDTO dto = new WarehouseDTO();
        dto.setName("Updated");

        when(warehouseService.updateWarehouse(eq(1), any()))
                .thenReturn(dto);

        mockMvc.perform(put("/api/warehouses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    void createWarehouse_shouldReturnCreated() throws Exception {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setName("New Warehouse");
        dto.setLocation("North");

        WarehouseDTO saved = new WarehouseDTO();
        saved.setId(2);
        saved.setName("New Warehouse");
        saved.setLocation("North");

        when(warehouseService.createWarehouse(any())).thenReturn(saved);

        mockMvc.perform(post("/api/warehouses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }
}
