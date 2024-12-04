package org.octoconta.octobackend.rest;

import jakarta.validation.Valid;
import org.octoconta.octobackend.dto.TransactionDTO;
import org.octoconta.octobackend.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionResource {

    private final TransactionService transactionService;

    TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Long> addTransaction(@RequestBody @Valid final TransactionDTO transactionDTO) {

        final Long userId = transactionDTO.getUser();
        final Long categoryId = transactionDTO.getCategory();

        final Long newTransactionId = transactionService.add(transactionDTO, userId, categoryId);
        return new ResponseEntity<>(newTransactionId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getUserTransactions(
            @RequestParam(value = "userId") Long userId
    ) {
        return ResponseEntity.ok(transactionService.getUserTransactions(userId));
    }

    @GetMapping("/category")
    public ResponseEntity<List<TransactionDTO>> getUsersTransactionsByCategory( @RequestParam(value = "userId") Long userId, @RequestParam(value = "categoryId") Long categoryId){
        return ResponseEntity.ok(transactionService.getUserTransactionsByCategory(userId, categoryId));
    }

}