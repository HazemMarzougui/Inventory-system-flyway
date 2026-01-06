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
@Table(name = "suppliers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String contact;
    @Column(name= "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "suppliers")
    @JsonIgnore
    private Set<Product> products;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
