package com.example.invertorymanagementsystem.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setup() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleBusinessErrors_shouldReturnBadRequest() {
        RuntimeException ex = new RuntimeException("Test business error");

        // ✅ Mock WebRequest using Mockito
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("uri=/test");

        ResponseEntity<?> response = handler.handleBusinessErrors(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Optionally, verify the body content
        assertNotNull(response.getBody());
    }
}
