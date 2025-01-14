package com.toren.shewstringbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toren.shewstringbe.dto.budgetdto.CreateBudgetDto;
import com.toren.shewstringbe.dto.budgetdto.UpdateBudgetDto;
import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.service.BudgetService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = {"/budget", "/api/budget"})
public class BudgetController {
  
  private final BudgetService budgetService;

  @Autowired
  public BudgetController(BudgetService budgetService) {
    this.budgetService = budgetService;
  }

  @GetMapping
  public ResponseEntity<List<Budget>> getAllBudgets() {
    return ResponseEntity.ok(budgetService.getAllBudgets());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
    return ResponseEntity.ok(budgetService.getBudgetById(id));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<Budget>> getBudgetsByUserId(@PathVariable String id) {
    return ResponseEntity.ok(budgetService.getBudgetsByUserId(id));
  }

  @GetMapping("/bankAccount/{id}")
  public ResponseEntity<List<Budget>> getBudgetsByBankAccountId(@PathVariable Long id) {
    return ResponseEntity.ok(budgetService.getBudgetsByBankAccount(id));
  }

  @PostMapping
  public ResponseEntity<Budget> createNewBudget(CreateBudgetDto createBudgetDto) {
    return ResponseEntity.ok(budgetService.createBudget(createBudgetDto));
  }

  @PutMapping()
  public ResponseEntity<Budget> updateBudget(UpdateBudgetDto updateBudgetDto) {
    return ResponseEntity.ok(budgetService.updateBudget(updateBudgetDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBudgetById(@PathVariable Long id) {
    budgetService.deleteBudget(id);
    return ResponseEntity.ok().build();
  }
}
