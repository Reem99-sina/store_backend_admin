package store_backend_admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import store_backend_admin.entity.UserRole;
import store_backend_admin.entity.UserStatus;

public class UpdateUserRequest {

    @Size(min = 2)
    private String fullName;

    @Email
    private String email;

    @Size(min = 6)
    private String password;

    private UserRole role;

    private UserStatus status;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}