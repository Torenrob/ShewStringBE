package com.toren.shewstringbe.repository;

import com.toren.shewstringbe.models.Category;
import com.toren.shewstringbe.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> getCategoriesByUserProfile(UserProfile userProfile);
}
