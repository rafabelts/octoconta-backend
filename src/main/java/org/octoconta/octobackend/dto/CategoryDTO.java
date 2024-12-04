package org.octoconta.octobackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import org.octoconta.octobackend.repos.CategoryRepository;

public class CategoryDTO {

    private Long categoryId;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String type;

    @Size(max = 255)
    private String icon;

    @Size(max = 255)
    private String color;

    @JsonProperty("isActive")
    private Boolean isActive=true;

    private Long user;

    public CategoryDTO(){}

    public CategoryDTO(String name, String type, String icon, String color) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.color = color;
    }

    // getters and setters
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(final String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public String getColor() {return color;}

    public void setColor(final String color) {this.color = color;}

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }


    public Long getUser() {
        return user;
    }
    public void setUser(final Long user) {
        this.user = user;
    }
}