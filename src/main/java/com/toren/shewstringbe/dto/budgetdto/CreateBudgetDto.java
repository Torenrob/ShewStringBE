package com.toren.shewstringbe.dto.budgetdto;

import java.time.LocalDate;
import java.util.List;

import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.models.Category;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateBudgetDto extends Budget {
  private String userId;
  private Long bankAccountId;
  private Boolean isAllTime;
  private LocalDate monthYear;
  private List<Category> budgetCategories;
}
