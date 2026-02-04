package com.example.invertorymanagementsystem.repositories;

import com.example.invertorymanagementsystem.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {}
