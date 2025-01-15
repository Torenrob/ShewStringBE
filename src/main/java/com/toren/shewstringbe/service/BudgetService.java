package com.toren.shewstringbe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toren.shewstringbe.dto.budgetdto.CreateBudgetDto;
import com.toren.shewstringbe.dto.budgetdto.UpdateBudgetDto;
import com.toren.shewstringbe.mapper.BudgetMapper;
import com.toren.shewstringbe.models.BankAccount;
import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.BudgetRepo;

@Service
public class BudgetService {
  
  private final BudgetRepo budgetRepo;
  private final BudgetMapper budgetMapper;
  private final BankAccountService bankAccountService;
  private final UserProfileService userProfileService;

  public BudgetService(BudgetRepo budgetRepo, BudgetMapper budgetMapper, BankAccountService bankAccountService, UserProfileService userProfileService) {
    this.budgetRepo = budgetRepo;
    this.budgetMapper = budgetMapper;
    this.bankAccountService = bankAccountService;
    this.userProfileService = userProfileService;
  }

  public List<Budget> getAllBudgets() {
    return budgetRepo.findAll();
  }

  public List<Budget> getBudgetsByUserId(String id) {
    return budgetRepo.getBudgetsByUserProfileId(id);
  }

  public List<Budget> getBudgetsByBankAccount(BankAccount bankAccount) {
    return budgetRepo.getBudgetsByBankAccountId(bankAccount.getId());
  }

  public Budget getBudgetByCategoryBudgetId(Long id) {
    return budgetRepo.getBudgetByCategoryId(id);
  }
  
  public List<Budget> getBudgetsByBankAccount(Long id) {
    return budgetRepo.getBudgetsByBankAccountId(id);
  }

  public Budget getBudgetById(Long id) {
    return budgetRepo.findById(id).orElseThrow();
  }

  public Budget removeCategoryFromBudgetById(Long categoryId, Long budgetId) {
    Budget budget = budgetRepo.findById(budgetId).orElseThrow(() -> new RuntimeException("Associated Budget not Found"));

    budget.getBudgetCategories().removeIf(category -> category.getId().equals(categoryId));
    return budgetRepo.save(budget);
  }

  public Budget createBudget(CreateBudgetDto createBudgetDto) {
    Budget budget = budgetMapper.fromCreateToBudget(createBudgetDto);

    UserProfile userProfile = userProfileService.getUserProfileById(createBudgetDto.getUserId()).orElseThrow(() -> new RuntimeException("Cant find associated User Profile"));
    BankAccount bankAccount = bankAccountService.getBankAccountById(createBudgetDto.getBankAccountId()).orElseThrow(() -> new RuntimeException("Cant find associated Bank Account"));

    budget.setBankAccount(bankAccount);
    budget.setUserProfile(userProfile);

    return budgetRepo.save(budget);
  }

  public Budget updateBudget(UpdateBudgetDto updateBudgetDto) {
    Budget budget = budgetRepo.findById(updateBudgetDto.getId()).orElseThrow(() -> new RuntimeException("Budget not found"));

    budget.setBudgetCategories(updateBudgetDto.getBudgetCategories());
    budget.setIsAllTime(updateBudgetDto.getIsAllTime());
    budget.setMonthYear(updateBudgetDto.getMonthYear());
    
    return budgetRepo.save(budget);
  }

  public void deleteBudget(Long id) {
    Budget budget = budgetRepo.findById(id).orElseThrow(() -> new RuntimeException("Budget Not Found"));

    budgetRepo.delete(budget);
  } 
}
