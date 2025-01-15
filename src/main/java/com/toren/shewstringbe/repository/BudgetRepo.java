package com.toren.shewstringbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.toren.shewstringbe.models.Budget;

import java.util.List;

public interface BudgetRepo extends JpaRepository<Budget, Long> {
  List<Budget> getBudgetsByBankAccountId(Long bankAccountId);

  List<Budget> getBudgetsByUserProfileId(String id);

  @Query("SELECT b FROM Budget b JOIN b.budgetCategories c WHERE c.id = :categoryId")
  Budget getBudgetByCategoryId(@Param("categoryId") Long categoryId);

}
