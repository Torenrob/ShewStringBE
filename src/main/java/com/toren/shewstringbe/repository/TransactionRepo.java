package com.toren.shewstringbe.repository;

import com.toren.shewstringbe.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionsByBankAccount_Id(Long id);
    List<Transaction> getTransactionsByCategory_Id(Long id);
}
