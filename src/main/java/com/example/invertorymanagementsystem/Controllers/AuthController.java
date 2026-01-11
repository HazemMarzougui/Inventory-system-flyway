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
        return ResponseEntity.ok(authService.register(dto));
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