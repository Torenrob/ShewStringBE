package com.toren.shewstringbe.service;

import com.toren.shewstringbe.dto.categorydto.CreateCategoryDto;
import com.toren.shewstringbe.dto.categorydto.CreateCategoryNewBudgetDto;
import com.toren.shewstringbe.dto.categorydto.UpdateCategoryDto;
import com.toren.shewstringbe.mapper.CategoryMapper;
import com.toren.shewstringbe.models.Category;
import com.toren.shewstringbe.models.Transaction;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;
    private final UserProfileService userProfileService;
    private final TransactionService transactionService;
    
    @Autowired
    public CategoryService(CategoryRepo categoryRepo, UserProfileService userProfileService, CategoryMapper categoryMapper, TransactionService transactionService) {
        this.categoryRepo = categoryRepo;
        this.userProfileService = userProfileService;
        this.categoryMapper = categoryMapper;
        this.transactionService = transactionService;
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

    public Category createCategory(CreateCategoryDto createCategoryDto) {
        Category category = categoryMapper.toCategoryFromCreateCateogory(createCategoryDto);

        return categoryRepo.save(category);
    }

    public Category createCategory(CreateCategoryNewBudgetDto createCategoryNewBudgetDto) {
        Category category = categoryMapper.toCategoryfromCreateCategoryNewBudget(createCategoryNewBudgetDto);

        return categoryRepo.save(category);
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

    public void deleteCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));

        for (Transaction t: category.getTransactions()) {
            UserProfile userProfile = category.getUserProfile();
            List<Category> userCategories = userProfile.getCategories();

            Category userDefaultNoneCategory = userCategories.stream().reduce(
                userCategories.getFirst(),
                (category1, category2) -> category1.getId() < category2.getId() ? category1 : category2
            );

            t.setCategory(userDefaultNoneCategory);
            transactionService.updateTransaction(t.getId(), t);
        }
        
        categoryRepo.delete(category);
    }
}
