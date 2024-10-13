package com.toren.shewstringbe.base;

import com.toren.shewstringbe.enums.AccountType;
import com.toren.shewstringbe.models.Transaction;
import lombok.Data;

import java.util.List;

@Data
public abstract class BankAccountBase {
    //No id in base - the creation dto will not have id
    private String title;
    private AccountType accountType;
    //Using Object Class so view can be changed in ResponseEntity
    private List<Transaction> transactions;

    public Object getTransactions() {
        return transactions;
    }
}