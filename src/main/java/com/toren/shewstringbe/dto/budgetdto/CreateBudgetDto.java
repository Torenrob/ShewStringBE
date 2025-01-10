package com.toren.shewstringbe.dto.budgetdto;

import java.time.LocalDate;
import java.util.List;

import com.toren.shewstringbe.models.Category;

import lombok.Data;

@Data
public class CreateBudgetDto {
  private String userId;
  private Long bankAccountId;
  private Boolean isAllTime;
  private LocalDate monthYear;
  private List<Category> budgetCategories;
}
