package com.example.invertorymanagementsystem.Controllers;

import com.example.invertorymanagementsystem.Dtos.LoginRequest;
import com.example.invertorymanagementsystem.Dtos.UserDTO;
import com.example.invertorymanagementsystem.Services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO dto) {
        UserDTO createdUser = authService.register(dto); // creates and saves the user
        // Return 201 CREATED with the user in the response body
        return ResponseEntity.status(201).body(createdUser);
    }



    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid
            @RequestBody LoginRequest request
    ) {
        String token = authService.login(
                request.getUsername(),
                request.getPassword()
        );
        return ResponseEntity.ok(Map.of("token", token));
    }
}