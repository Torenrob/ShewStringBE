package com.toren.shewstringbe.dto.bankaccountdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toren.shewstringbe.base.BankAccountBase;
import com.toren.shewstringbe.enums.AccountType;
import com.toren.shewstringbe.mapper.TransactionMapper;
import com.toren.shewstringbe.models.Budget;
import com.toren.shewstringbe.models.Transaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @Autowired
    public ReturnBankAccountDto(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TreeMap<String, List<Transaction>> getTransactions() {
        return transactionMapper.fromListToSortedMap(transactions);
    }

    @JsonIgnore
    public TransactionMapper getTransactionMapper() {
        return transactionMapper;
    }

}

