package com.toren.shewstringbe.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toren.shewstringbe.dto.budgetdto.CreateBudgetDto;
import com.toren.shewstringbe.models.Budget;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BudgetMapper {

  private final ModelMapper modelMapper;
  
  @Autowired
  public BudgetMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public Budget fromCreateToBudget(CreateBudgetDto createBudgetDto) {
    return modelMapper.map(createBudgetDto, Budget.class);
  }
}
