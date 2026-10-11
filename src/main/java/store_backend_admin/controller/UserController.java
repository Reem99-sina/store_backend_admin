package store_backend_admin.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store_backend_admin.dto.UpdateUserRequest;
import store_backend_admin.dto.CreateUserRequest;
import store_backend_admin.entity.User;
import store_backend_admin.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET /users/:id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Integer id
    ) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}")
    public User updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        return userService.updateUser(id, request);
    }
    // POST /users
    @PostMapping
    public User createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {
        return userService.createUser(request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Integer id
    ) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}