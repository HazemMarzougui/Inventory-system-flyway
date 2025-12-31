package com.example.invertorymanagementsystem.Repositories;

import com.example.invertorymanagementsystem.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}

