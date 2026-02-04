package com.example.invertorymanagementsystem;

import com.example.invertorymanagementsystem.dtos.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthenticationTests extends BaseIntegrationTest {

    @Test
    void shouldRegisterUserSuccessfully() {

        UserDTO signupRequest = new UserDTO();
        signupRequest.setUsername("tester");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("password123");

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "/api/auth/register",
                        signupRequest,
                        String.class
                );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
