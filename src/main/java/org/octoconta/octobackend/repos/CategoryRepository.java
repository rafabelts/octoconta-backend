package org.octoconta.octobackend.repos;

import org.octoconta.octobackend.domain.Category;
import org.octoconta.octobackend.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Find by user id
    List<Category> findByUser_UserId(Long userId);

}