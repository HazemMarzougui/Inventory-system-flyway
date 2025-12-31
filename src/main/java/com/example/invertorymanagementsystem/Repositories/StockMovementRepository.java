package com.example.invertorymanagementsystem.Repositories;

import com.example.invertorymanagementsystem.Entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Integer> {}
