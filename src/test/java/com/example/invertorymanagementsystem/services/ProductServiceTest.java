package com.example.invertorymanagementsystem.services;

import com.example.invertorymanagementsystem.dtos.ProductDTO;
import com.example.invertorymanagementsystem.entities.Product;
import com.example.invertorymanagementsystem.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    // CREATE
    @Test
    void createProduct_shouldSaveAndReturnDTO() {

        ProductDTO dto = new ProductDTO();
        dto.setName("Laptop");
        dto.setPrice(BigDecimal.valueOf(1200.0));
        dto.setCategory("IT");

        Product saved = new Product();
        saved.setId(1);
        saved.setName("Laptop");
        saved.setPrice(BigDecimal.valueOf(1200.0));
        saved.setCategory("IT");

        when(productRepository.save(any(Product.class)))
                .thenReturn(saved);

        ProductDTO result = productService.createProduct(dto);

        assertNotNull(result.getId());
        assertEquals("Laptop", result.getName());

        verify(productRepository).save(any(Product.class));
    }

    // GET ALL
    @Test
    void getAllProducts_shouldReturnList() {

        Product p = new Product();
        p.setId(1);
        p.setName("Mouse");

        when(productRepository.findAll())
                .thenReturn(List.of(p));

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());
        verify(productRepository).findAll();
    }

    // GET BY ID
    @Test
    void getProductById_shouldReturnDTO() {

        Product p = new Product();
        p.setId(1);
        p.setName("Keyboard");

        when(productRepository.findById(1))
                .thenReturn(Optional.of(p));

        ProductDTO dto = productService.getProductById(1);

        assertEquals("Keyboard", dto.getName());
    }

    // UPDATE
    @Test
    void updateProduct_shouldModifyProduct() {

        Product existing = new Product();
        existing.setId(1);

        ProductDTO dto = new ProductDTO();
        dto.setName("Updated");

        when(productRepository.findById(1))
                .thenReturn(Optional.of(existing));

        when(productRepository.save(existing))
                .thenReturn(existing);

        ProductDTO result = productService.updateProduct(1, dto);

        assertEquals("Updated", result.getName());
    }

    // DELETE
    @Test
    void deleteProduct_shouldCallRepository() {

        when(productRepository.existsById(1))
                .thenReturn(true);

        productService.deleteProduct(1);

        verify(productRepository).deleteById(1);
    }

    // DELETE NOT FOUND
    @Test
    void deleteProduct_notFound_shouldThrow() {

        when(productRepository.existsById(1))
                .thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> productService.deleteProduct(1));
    }
}
