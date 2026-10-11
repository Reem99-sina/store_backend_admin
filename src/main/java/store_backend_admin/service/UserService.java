package store_backend_admin.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import store_backend_admin.dto.CreateUserRequest;
import store_backend_admin.dto.UpdateUserRequest;
import store_backend_admin.dto.RegisterRequest;
import store_backend_admin.entity.User;
import store_backend_admin.entity.UserRole;
import store_backend_admin.entity.UserStatus;
import store_backend_admin.repository.UserRepository;
import store_backend_admin.dto.LoginRequest;
import store_backend_admin.dto.LoginResponse;
import store_backend_admin.dto.UserResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UserService(
            UserRepository userRepository,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    // GET ALL
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET BY ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // CREATE
    public User createUser(CreateUserRequest request) {

        // Check email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Hash password
        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);

        user.setRole(
                request.getRole() != null
                        ? request.getRole()
                        : UserRole.CUSTOMER
        );

        user.setStatus(
                request.getStatus() != null
                        ? request.getStatus()
                        : UserStatus.ACTIVE
        );

        return userRepository.save(user);
    }
    public User updateUser(
            Integer id,
            UpdateUserRequest request
    ) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found.")
                );

        // Update full name
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }

        // Update email
        if (request.getEmail() != null
                && !request.getEmail().equals(user.getEmail())) {

            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email already exists");
            }

            user.setEmail(request.getEmail());
        }

        // Update password
        if (request.getPassword() != null) {
            user.setPassword(
                    passwordEncoder.encode(request.getPassword())
            );
        }

        // Update role
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }

        // Update status
        if (request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }

        return userRepository.save(user);
    }
    public User deleteUser(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found.")
                );

        userRepository.delete(user);

        return user;
    }
    public User register(RegisterRequest request) {

        // Check email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Hash password
        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword);

        user.setRole(
                request.getRole() != null
                        ? request.getRole()
                        : UserRole.CUSTOMER
        );

        // Register always creates ACTIVE user
        user.setStatus(UserStatus.ACTIVE);

        return userRepository.save(user);
    }
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password")
                );

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("User account is inactive");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        return new LoginResponse(token, userResponse);
    }
}