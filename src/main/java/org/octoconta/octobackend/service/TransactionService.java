package org.octoconta.octobackend.service;

import org.octoconta.octobackend.domain.Category;
import org.octoconta.octobackend.domain.Transaction;
import org.octoconta.octobackend.domain.User;
import org.octoconta.octobackend.dto.TransactionDTO;
import org.octoconta.octobackend.repos.CategoryRepository;
import org.octoconta.octobackend.repos.TransactionRepository;
import org.octoconta.octobackend.repos.UserRepository;
import org.octoconta.octobackend.util.NotFoundException;
import org.octoconta.octobackend.util.ReferencedWarning;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    final UserRepository userRepository;
    final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    TransactionService(UserRepository userRepository, CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    public Long add(TransactionDTO transactionDTO, Long userId, Long categoryId) {
        final Transaction transaction = new Transaction();
        mapToEntity(transactionDTO, transaction, userId, categoryId);
        return transactionRepository.save(transaction).getTransactionId();
    }

    public void update(final Long transactionId, final TransactionDTO transactionDTO, Long userId, Long categoryId) {
        final Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(NotFoundException::new);

        mapToEntity(transactionDTO, transaction, userId, categoryId);

        transactionRepository.save(transaction);
    }

    public void delete(final Long transactionId){
        final Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new NotFoundException("Category not found"));

        transaction.setIsActive(false);
    }

    public List<TransactionDTO> getUserTransactions(Long userId){
        final List<Transaction> transactions = transactionRepository.findByUser_UserId(userId);

        return transactions.stream().map(
                transaction -> mapToDTO(transaction, new TransactionDTO(), userId, transaction.getCategory().getCategoryId(), transaction.getCategory().getIcon(), transaction.getCategory().getColor())
        ).collect(Collectors.toList());
    }

    public List<TransactionDTO> getUserTransactionsByCategory(Long userId, Long categoryId){
        final List<Transaction> transactions = transactionRepository.findByUser_UserIdAndCategory_CategoryId(userId, categoryId);

        return transactions.stream().map(
                transaction -> mapToDTO(transaction, new TransactionDTO(), userId, categoryId, transaction.getCategory().getIcon(), transaction.getCategory().getColor())
        ).collect(Collectors.toList());
    }

    private TransactionDTO mapToDTO(final Transaction transaction, final TransactionDTO transactionDTO, final Long userId, final Long categoryId, final String categoryIcon, final String categoryColor) {
        transactionDTO.setTransactionId(transaction.getTransactionId());
        transactionDTO.setTitle(transaction.getTitle());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setDate(transaction.getDate());
        transactionDTO.setUser(userId);
        transactionDTO.setCategory(categoryId);
        transactionDTO.setIsActive(transaction.getIsActive());
        transactionDTO.setCategoryIcon(categoryIcon);
        transactionDTO.setCategoryColor(categoryColor);

        return transactionDTO;
    }

    private Transaction mapToEntity(final TransactionDTO transactionDTO, final Transaction transaction, final Long userId, final Long categoryId) {
        transaction.setTitle(transactionDTO.getTitle());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setDate(transactionDTO.getDate());
        transaction.setIsActive(transactionDTO.getIsActive());

        final User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        transaction.setUser(user);

        final Category category = categoryRepository.findById(categoryId).orElseThrow(()->new NotFoundException("Category not found with ID: " + categoryId));
        transaction.setCategory(category);

        return transaction;
    }

    public ReferencedWarning getReferencedWarning(final Long transactionId) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(NotFoundException::new);

       return null;
    }
}
