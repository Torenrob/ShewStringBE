package com.toren.shewstringbe.dto.bankaccountdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.toren.shewstringbe.base.BankAccountBase;
import com.toren.shewstringbe.config.ApplicationConfig;
import com.toren.shewstringbe.enums.AccountType;
import com.toren.shewstringbe.mapper.TransactionMapper;
import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.models.Transaction;
import com.toren.shewstringbe.repository.BudgetRepo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeMap;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class ReturnBankAccountDto extends BankAccountBase {
    private Long id;
    // private List<Object> repeatGroups;
    private String title;
    private List<Transaction> transactions;
    private AccountType accountType;
    private List<Budget> budgets;
    @JsonIgnore
    private final TransactionMapper transactionMapper;
    @JsonIgnore
    private final BudgetRepo budgetRepo;

    @Autowired
    public ReturnBankAccountDto(TransactionMapper transactionMapper, BudgetRepo budgetRepo) {
        this.transactionMapper = transactionMapper;
        this.budgetRepo = budgetRepo;
    }

    @Override
    public TreeMap<String, List<Transaction>> getTransactions() {
        return transactionMapper.fromListToSortedMap(transactions);
    }

    @Override
    public List<Budget> getBudgets(){
        return budgetRepo.getBudgetsByBankAccountId(id);
    }

    @SuppressWarnings("SameReturnValue")
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = ApplicationConfig.ExcludeNull.class)
    public ModelMapper getModelMapper() {
        return null;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + id +
            ", title=" + title +
            ", accountType=" + accountType +
            ", budgets=" + budgets +
            ", transactions=" + transactions +
        '}';
    }
}
