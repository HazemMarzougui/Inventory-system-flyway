package com.example.invertorymanagementsystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String location;
    @Column(name= "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnore
    private Set<StockMovement> stockMovements;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
