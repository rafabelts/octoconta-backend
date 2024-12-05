package org.octoconta.octobackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLogInDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must be less than 255 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(max = 255, message = "Password must be less than 255 characters")
    private String password;

    // constructors
    public UserLogInDTO() {}

    public UserLogInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}