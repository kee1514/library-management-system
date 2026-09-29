package com.library.library_management_system.controller;

import com.library.library_management_system.dto.LoginRequest;
import com.library.library_management_system.dto.LoginResponse;
import com.library.library_management_system.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {

        return authService.login(
                request,
                httpRequest,
                httpResponse
        );
    }
}