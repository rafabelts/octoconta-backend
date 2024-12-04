package org.octoconta.octobackend.rest;

import org.octoconta.octobackend.dto.CategoryDTO;
import org.octoconta.octobackend.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryResource {
    private final CategoryService categoryService;

    public CategoryResource(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getUsersCategory(
          @RequestParam(value = "userId") Long userId
    ) {
        return ResponseEntity.ok(categoryService.getUserCategories(userId));
    }

}


