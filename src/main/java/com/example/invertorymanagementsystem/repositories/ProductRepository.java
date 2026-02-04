package com.example.invertorymanagementsystem.repositories;

import com.example.invertorymanagementsystem.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}

