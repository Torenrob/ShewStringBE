package com.toren.shewstringbe.mapper;

import com.toren.shewstringbe.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Component
public class TransactionMapper {

    public TreeMap<String, List<Transaction>> fromListToSortedMap(List<Transaction> transactionList) {
        TreeMap<String,List<Transaction>> transactionTreeMap = new TreeMap<>();

        if(transactionList == null) {
            return transactionTreeMap;
        }

        for (Transaction t: transactionList) {
            String date = t.getDate().toString();

            if (transactionTreeMap.containsKey(date)) {
                transactionTreeMap.get(date).add(t);
            } else {
                transactionTreeMap.put(date, new ArrayList<>(List.of(t)));
            }
        }

        return transactionTreeMap;
    }
}
