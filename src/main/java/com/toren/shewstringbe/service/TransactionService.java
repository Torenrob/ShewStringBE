package com.toren.shewstringbe.service;

import com.toren.shewstringbe.models.Transaction;
import com.toren.shewstringbe.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepo.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepo.deleteById(id);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        return transactionRepo.findById(id)
                .map( t -> {
                    t.setTitle(transaction.getTitle());
                    t.setUserProfile(transaction.getUserProfile());
                    t.setDate(transaction.getDate());
                    t.setAmount(transaction.getAmount());
                    t.setCategory(transaction.getCategory());
                    t.setTransactionType(transaction.getTransactionType());
                    t.setDescription(transaction.getDescription());
                    t.setBankAccount(transaction.getBankAccount());
                    return transactionRepo.save(t);
                })
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}
