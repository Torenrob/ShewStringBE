package com.toren.shewstringbe.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toren.shewstringbe.dto.categorydto.CreateCategoryDto;
import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.models.Category;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.service.BudgetService;
import com.toren.shewstringbe.service.UserProfileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CategoryMapper {

  private final BudgetService budgetService;
  private final UserProfileService userProfileService;
  private final ModelMapper modelMapper;

  @Autowired
  public CategoryMapper(BudgetService budgetService, UserProfileService userProfileService, ModelMapper modelMapper) {
    this.budgetService = budgetService;
    this.userProfileService = userProfileService;
    this.modelMapper = modelMapper;
  }

  public Category toCategoryFromCreate(CreateCategoryDto createCategoryDto) {
    UserProfile userProfile = userProfileService.getUserProfileById(createCategoryDto.getUserId()).orElseThrow();

    Budget budget = budgetService.getBudgetById(createCategoryDto.getBankAccountId());

    Category category = modelMapper.map(createCategoryDto, Category.class);

    category.setUserProfile(userProfile);
    category.setBudget(budget);

    return category;
  }

  public CreateCategoryDto toCreateFromCategoryDto(Category category) {
    return modelMapper.map(category, CreateCategoryDto.class);
  }
}
