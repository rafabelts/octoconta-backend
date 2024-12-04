package org.octoconta.octobackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.octoconta.octobackend.domain.User;

public class UserDTO {

    private Long userId;

    @NotBlank(message = "Name is required")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Email is required")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(max = 255)
    private String password;

    @JsonProperty("isActive")
    private Boolean isActive=true;

    // constructors
    public UserDTO() {}

    public UserDTO(User user) {

        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();

    }

    // getters and setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

}
