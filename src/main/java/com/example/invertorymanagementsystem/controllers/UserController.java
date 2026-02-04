package com.example.invertorymanagementsystem.controllers;

import com.example.invertorymanagementsystem.dtos.UserDTO;
import com.example.invertorymanagementsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // secured via security config
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE USER
    @PostMapping
    public UserDTO createUser(@Valid  @RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    // GET ALL USERS
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
