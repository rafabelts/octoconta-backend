package org.octoconta.octobackend.repos;

import org.octoconta.octobackend.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser_UserId(Long userId);

    List<Transaction> findByUser_UserIdAndCategory_CategoryId(Long userId, Long categoryId);

}