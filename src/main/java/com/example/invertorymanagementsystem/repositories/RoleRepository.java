package com.example.invertorymanagementsystem.repositories;

import com.example.invertorymanagementsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
    List<Role> findByNameIn(Set<String> names);
}
