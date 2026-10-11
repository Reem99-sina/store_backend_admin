package store_backend_admin.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import store_backend_admin.dto.LoginRequest;
import store_backend_admin.dto.LoginResponse;
import store_backend_admin.dto.RegisterRequest;
import store_backend_admin.entity.User;
import store_backend_admin.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return userService.login(request);
    }
}