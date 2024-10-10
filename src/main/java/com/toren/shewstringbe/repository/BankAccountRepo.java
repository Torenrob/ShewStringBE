package com.toren.shewstringbe.repository;

import com.toren.shewstringbe.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
}
