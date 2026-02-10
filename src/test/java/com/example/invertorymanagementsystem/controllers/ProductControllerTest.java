package com.example.invertorymanagementsystem.controllers;

import com.example.invertorymanagementsystem.dtos.ProductDTO;
import com.example.invertorymanagementsystem.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import com.example.invertorymanagementsystem.security.JwtAuthenticationFilter;

@WebMvcTest(
        controllers = ProductController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class,
        SecurityFilterAutoConfiguration.class},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = JwtAuthenticationFilter.class
        )
)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    // GET ALL
    @Test
    void getAllProducts_shouldReturnList() throws Exception {

        ProductDTO dto = new ProductDTO();
        dto.setId(1);
        dto.setName("Mouse");

        when(productService.getAllProducts())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mouse"));
    }

    // GET BY ID
    @Test
    void getProductById_shouldReturnProduct() throws Exception {

        ProductDTO dto = new ProductDTO();
        dto.setId(1);
        dto.setName("Keyboard");

        when(productService.getProductById(1))
                .thenReturn(dto);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"));
    }

    // CREATE
    @Test
    void createProduct_shouldReturnCreated() throws Exception {

        ProductDTO input = new ProductDTO();
        input.setName("Laptop");
        input.setPrice(BigDecimal.valueOf(1000.0));
        input.setCategory("Electronics");

        ProductDTO output = new ProductDTO();
        output.setId(1);
        output.setName("Laptop");
        output.setPrice(BigDecimal.valueOf(1000.0));
        output.setCategory("Electronics");

        when(productService.createProduct(any()))
                .thenReturn(output);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    // UPDATE
    @Test
    void updateProduct_shouldReturnUpdated() throws Exception {

        ProductDTO dto = new ProductDTO();
        dto.setName("Updated");

        when(productService.updateProduct(eq(1), any()))
                .thenReturn(dto);

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    // DELETE
    @Test
    void deleteProduct_shouldReturnNoContent() throws Exception {

        doNothing().when(productService).deleteProduct(1);

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}
