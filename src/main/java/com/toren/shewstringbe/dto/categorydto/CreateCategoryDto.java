package com.toren.shewstringbe.dto.categorydto;

import java.math.BigDecimal;

import com.toren.shewstringbe.enums.BudgetCategoryType;

import lombok.Data;

@Data
public class CreateCategoryDto {
  private String userId;
  private Long BankAccountId;
  private String title;
  private BigDecimal amount;
  private String color;
  private BudgetCategoryType type;
}
