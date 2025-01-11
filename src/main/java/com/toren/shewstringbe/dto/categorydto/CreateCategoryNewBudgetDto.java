package com.toren.shewstringbe.dto.categorydto;

import java.math.BigDecimal;

import com.toren.shewstringbe.dto.budgetdto.CreateBudgetDto;
import com.toren.shewstringbe.enums.BudgetCategoryType;

import lombok.Data;

@Data
public class CreateCategoryNewBudgetDto {
  private String userId;
  private CreateBudgetDto budget;
  private String title;
  private BigDecimal amount;
  private String color;
  private BudgetCategoryType type;
}
