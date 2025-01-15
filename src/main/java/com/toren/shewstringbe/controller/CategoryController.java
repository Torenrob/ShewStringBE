package com.toren.shewstringbe.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toren.shewstringbe.dto.categorydto.CreateCategoryDto;
import com.toren.shewstringbe.dto.categorydto.CreateCategoryNewBudgetDto;
import com.toren.shewstringbe.dto.categorydto.UpdateCategoryDto;
import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.models.Category;
import com.toren.shewstringbe.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = {"/category", "/api/category"})
public class CategoryController {
  
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<Category>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<Category>> getCategoriesByUserId(@PathVariable String id) {
    return ResponseEntity.ok(categoryService.getAllCategoriesByUserId(id));
  }

  @GetMapping("/budget/{id}")
  public ResponseEntity<List<Category>> getCategoriesByBudgetId(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getCategoriesByBudgetId(id));
  }

  @PostMapping
  public ResponseEntity<Budget> createNewCategoryExistingBudget(@RequestBody CreateCategoryDto createCategoryDto) {
    log.info("Create Category Existing Budget Request Received");

    return ResponseEntity.ok(categoryService.createCategory(createCategoryDto));
  }

  @PostMapping("/newBudget")
  public ResponseEntity<Budget> createNewCategoryNewBudget(@RequestBody CreateCategoryNewBudgetDto createCategoryNewBudgetDto) {
    log.info("Create Category with New Budget Request Received");

    return ResponseEntity.ok(categoryService.createCategory(createCategoryNewBudgetDto));
  }


  @PutMapping
  public ResponseEntity<Category> updateCategoryById(@RequestBody UpdateCategoryDto UpdateCategoryDto) {
    return ResponseEntity.ok(categoryService.updateCategoryById(UpdateCategoryDto));
  }

  @DeleteMapping("/{id}/{userId}")
  public ResponseEntity<Budget> deleteCategoryById(@PathVariable Long id, @PathVariable String userId) {
    log.info("Request to delete budget id: " + id);

    return ResponseEntity.ok(categoryService.deleteCategoryById(id, userId));
  }
}
