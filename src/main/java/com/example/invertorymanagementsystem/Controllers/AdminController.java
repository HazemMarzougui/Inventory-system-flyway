package com.example.invertorymanagementsystem.Controllers;


import com.example.invertorymanagementsystem.Dtos.UserDTO;
import com.example.invertorymanagementsystem.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Create STAFF user
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createStaff( @Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.createStaff(dto));
    }

    // Promote STAFF -> ADMIN
    @PutMapping("/users/{id}/promote")
    public ResponseEntity<Void> promoteToAdmin(@PathVariable Integer id) {
        userService.promoteToAdmin(id);
        return ResponseEntity.ok().build();
    }

    // List all users
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
