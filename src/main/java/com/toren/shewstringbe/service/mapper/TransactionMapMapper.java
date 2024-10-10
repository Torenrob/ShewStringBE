package com.toren.shewstringbe.service.mapper;

import com.toren.shewstringbe.models.Transaction;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.*;

@Converter
public class TransactionMapMapper implements AttributeConverter<TreeMap<String, List<Transaction>>, List<Transaction>> {

    @Override
    public List<Transaction> convertToDatabaseColumn(TreeMap<String, List<Transaction>> transactionListTreeMap) {
        return transactionListTreeMap.values().stream().flatMap(List::stream).toList();
    }

    @Override
    public TreeMap<String, List<Transaction>> convertToEntityAttribute(List<Transaction> transactionList) {
        TreeMap<String,List<Transaction>> transactionTreeMap = new TreeMap<>();

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
