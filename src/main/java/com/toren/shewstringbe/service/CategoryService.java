package com.toren.shewstringbe.service;

import com.toren.shewstringbe.dto.categorydto.CreateCategoryDto;
import com.toren.shewstringbe.dto.categorydto.CreateCategoryNewBudgetDto;
import com.toren.shewstringbe.dto.categorydto.UpdateCategoryDto;
import com.toren.shewstringbe.mapper.CategoryMapper;
import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.models.Category;
import com.toren.shewstringbe.models.Transaction;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.CategoryRepo;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;
    private final BudgetService budgetService;
    private final UserProfileService userProfileService;
    private final TransactionService transactionService;
    
    @Autowired
    public CategoryService(CategoryRepo categoryRepo, UserProfileService userProfileService, CategoryMapper categoryMapper, TransactionService transactionService, BudgetService budgetService) {
        this.categoryRepo = categoryRepo;
        this.userProfileService = userProfileService;
        this.categoryMapper = categoryMapper;
        this.transactionService = transactionService;
        this.budgetService = budgetService;
    }
    
    public List<Category> getAllCategories() {return categoryRepo.findAll();}
    
    public List<Category> getAllCategoriesByUserId(String userId) {
        UserProfile userProfile = userProfileService.getUserProfileById(userId).orElseThrow();
        
        return categoryRepo.getCategoriesByUserProfile(userProfile);
    }

    public List<Category> getCategoriesByBudgetId(Long id) {
        return categoryRepo.getCategoriesByBudgetId(id);
    }
    
    public Category getCategoryById(Long id) {return categoryRepo.getReferenceById(id);}

    public Budget createCategory(CreateCategoryDto createCategoryDto) {
        log.debug("Requesting to map create category to Category class");
        Category category = categoryMapper.toCategoryFromCreateCateogory(createCategoryDto);
        log.debug("Create Category object mapped to Category Class");

        category = categoryRepo.save(category);

        Budget budget = budgetService.getBudgetById(createCategoryDto.getBudget().getId());

        budget.getBudgetCategories().add(category);

        return budget;
    }

    public Budget createCategory(CreateCategoryNewBudgetDto createCategoryNewBudgetDto) {
        Category category = categoryMapper.toCategoryfromCreateCategoryNewBudget(createCategoryNewBudgetDto);

        categoryRepo.save(category);

        Budget budget = budgetService.getBudgetById(category.getBudget().getId());

        budget.getBudgetCategories().add(category);

        return budget;
    }

    public Category updateCategoryById(UpdateCategoryDto updateCategoryDto) {
        return categoryRepo.findById(updateCategoryDto.getId()).map(
            cat -> {
                cat.setTitle(updateCategoryDto.getTitle());
                cat.setAmount(updateCategoryDto.getAmount());
                cat.setColor(updateCategoryDto.getColor());
                return categoryRepo.save(cat);
            }
        ).orElseThrow(() -> new RuntimeException("Category Not Found"));
    } 

    @Transactional
    public Budget deleteCategoryById(Long categoryId, String userId) {
        Category categoryToDelete = categoryRepo.getReferenceById(categoryId);

        UserProfile userProfile = userProfileService.removeCategoryFromUserProfileById(userId, categoryId);

        Budget budget = budgetService.getBudgetByCategoryBudgetId(categoryId);
        
        List<Transaction> categoryTransactions = transactionService.getTransactionsByCategoryId(categoryId);
        
        Category userDefaultNoneCategory = userProfile.getCategories().getFirst();
        
        for (Transaction t: categoryTransactions) {
            t.setCategory(userDefaultNoneCategory);
            transactionService.updateTransaction(t.getId(), t);
        }
        
        Budget budgetCategoryRemoved = budgetService.removeCategoryFromBudgetById(categoryId, budget.getId());

        log.warn("Got here");
        categoryRepo.delete(categoryToDelete);
        log.warn("And here");

        return budgetCategoryRemoved;
    }
}
