package com.toren.shewstringbe.dto.userdto;

import com.toren.shewstringbe.base.UserProfileBase;
import com.toren.shewstringbe.mapper.TransactionMapper;
import com.toren.shewstringbe.models.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.TreeMap;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoggedInDto extends UserProfileBase {
    private String id;
    private String token;
    private List<Transaction> transactions;

    @Override
    public TreeMap<String,List<Transaction>> getTransactions() {
        return new TransactionMapper().fromListToSortedMap(transactions);
    }
}
