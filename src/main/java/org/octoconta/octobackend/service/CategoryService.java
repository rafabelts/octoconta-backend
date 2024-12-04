package org.octoconta.octobackend.service;

import org.octoconta.octobackend.domain.Category;
import org.octoconta.octobackend.domain.User;
import org.octoconta.octobackend.dto.CategoryDTO;
import org.octoconta.octobackend.repos.CategoryRepository;
import org.octoconta.octobackend.repos.UserRepository;
import org.octoconta.octobackend.util.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    CategoryService(final UserRepository userRepository, CategoryRepository categoryRepository){
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Long add(CategoryDTO categoryDTO, Long userId) {
        final Category category = new Category();
        mapToEntity(categoryDTO, category, userId);
        return categoryRepository.save(category).getCategoryId();
    }

    public List<CategoryDTO> getUserCategories(Long userId) {
        // Fetch categories by userId
        final List<Category> categories = categoryRepository.findByUser_UserId(userId);

        // Convert to CategoryDTO
        return categories.stream()
                .map(category -> mapToDTO(category, new CategoryDTO(), userId))
                .collect(Collectors.toList());
    }

    private CategoryDTO mapToDTO(Category category, final CategoryDTO categoryDTO, final Long userId) {
      categoryDTO.setCategoryId(category.getCategoryId());
      categoryDTO.setName(category.getName());
      categoryDTO.setIcon(category.getIcon());
      categoryDTO.setType(category.getType());
      categoryDTO.setColor(category.getColor());
      categoryDTO.setIsActive(category.getIsActive());
      categoryDTO.setUser(userId);
      categoryDTO.setIsActive(category.getIsActive());
      return categoryDTO;
    }

    private Category mapToEntity(final CategoryDTO categoryDTO, final Category category, final Long userId) {
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());
        category.setIcon(categoryDTO.getIcon());
        category.setColor(categoryDTO.getColor());
        category.setIsActive(categoryDTO.getIsActive());

        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        category.setUser(user);

        return category;
    }
}
