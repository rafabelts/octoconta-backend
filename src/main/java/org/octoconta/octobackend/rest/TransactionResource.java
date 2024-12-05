package org.octoconta.octobackend.rest;

import jakarta.validation.Valid;
import org.octoconta.octobackend.dto.TransactionDTO;
import org.octoconta.octobackend.service.TransactionService;
import org.octoconta.octobackend.util.ReferencedException;
import org.octoconta.octobackend.util.ReferencedWarning;
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

    @PutMapping("/{transactionId}")
    public ResponseEntity<Long> updateTransaction(@PathVariable(name="transactionId") final Long transactionId, @RequestBody @Valid final TransactionDTO transactionDTO) {
        final Long userId = transactionDTO.getUser();
        final Long categoryId = transactionDTO.getCategory();

        transactionService.update(transactionId, transactionDTO, userId, categoryId);

        return ResponseEntity.ok(transactionId);

    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(name = "transactionId") final Long transactionId) {
        final ReferencedWarning referencedWarning = transactionService.getReferencedWarning(transactionId);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }

        transactionService.delete(transactionId);
        return ResponseEntity.noContent().build();
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