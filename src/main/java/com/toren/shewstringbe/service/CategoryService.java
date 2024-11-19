package com.toren.shewstringbe.service;

import com.toren.shewstringbe.models.Category;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final UserProfileService userProfileService;
    
    @Autowired
    public CategoryService(CategoryRepo categoryRepo, UserProfileService userProfileService) {
        this.categoryRepo = categoryRepo;
        this.userProfileService = userProfileService;
    }
    
    public List<Category> getAllCategories() {return categoryRepo.findAll();}
    
    public List<Category> getAllCategoriesByUserId(String userId) {
        UserProfile userProfile = userProfileService.getUserProfileById(userId).orElseThrow();
        
        return categoryRepo.getCategoriesByUserProfile(userProfile);
    }
    
    public Category getCategoryById(Long id) {return categoryRepo.getReferenceById(id);}
}
