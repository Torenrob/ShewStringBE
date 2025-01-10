package com.toren.shewstringbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toren.shewstringbe.models.Budget;

import java.util.List;

public interface BudgetRepo extends JpaRepository<Budget, Long> {
  List<Budget> getBudgetsByBankAccountId(Long bankAccountId);

  List<Budget> getBudgetsByUserProfileId(String id);
}
