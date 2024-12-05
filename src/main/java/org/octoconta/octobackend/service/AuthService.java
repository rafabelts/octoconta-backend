package org.octoconta.octobackend.service;

import org.aspectj.weaver.ast.Not;
import org.octoconta.octobackend.domain.User;
import org.octoconta.octobackend.dto.CategoryDTO;
import org.octoconta.octobackend.dto.UserDTO;
import org.octoconta.octobackend.repos.CategoryRepository;
import org.octoconta.octobackend.repos.UserRepository;
import org.octoconta.octobackend.util.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
   final UserRepository userRepository;
   final CategoryRepository categoryRepository;

    public AuthService(final UserRepository userRepository, final CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Long create(UserDTO userDTO) {
        final User user = new User();

        final CategoryService categoryService = new CategoryService(userRepository, categoryRepository);

        mapToEntity(userDTO, user);

       Long userId =  userRepository.save(user).getUserId();

       if(userId != null) {
           List<CategoryDTO> customCategories = Arrays.asList(
                   new CategoryDTO("Alimentos", "expense", "🥘", "#B91F1F"),
                   new CategoryDTO("Salud e higiene", "expense", "🏥", "#281F3D"),
                   new CategoryDTO("Suscripciones", "expense", "📝", "#755BD0"),
                   new CategoryDTO("Servicios", "expense", "💻", "#FFCE1F"),
                   new CategoryDTO("Otro", "expense", "💲", "#271E3C"),
                   new CategoryDTO("Trabajo", "income", "💻", "#FFCE1F"),
                  new CategoryDTO("Inversiones", "income", "📈", "#D4CCF0"),
                   new CategoryDTO("Otro", "income", "💲", "#271E3C")
           );

           for(CategoryDTO categoryDTO : customCategories) {
               categoryService.add(categoryDTO, userId);
           }
       }

        return userId;
    }

    public Long getIdByEmail(String email) {
        // Find the user by email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        return user.getUserId();
    }

    public Long logIn(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getPassword().equals(password)) {
            return user.getUserId();
        } else {
            throw new NotFoundException("Invalid password");
        }
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setIsActive(user.getIsActive());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setIsActive(userDTO.getIsActive());
        return user;
    }
}
