package com.toren.shewstringbe.base;

import com.toren.shewstringbe.enums.AccountType;
import com.toren.shewstringbe.models.Budget;
import lombok.Data;

import java.util.List;

@Data
public abstract class BankAccountBase {
    private String title;
    private AccountType accountType;
    private List<Budget> budgets;


    public abstract Object getTransactions();
}
