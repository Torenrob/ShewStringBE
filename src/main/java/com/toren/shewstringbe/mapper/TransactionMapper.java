package com.toren.shewstringbe.mapper;

import com.toren.shewstringbe.dto.transactiondto.ReturnTransactionDto;
import com.toren.shewstringbe.dto.transactiondto.SubmitTransactionDto;
import com.toren.shewstringbe.dto.transactiondto.UpdateTransactionDto;
import com.toren.shewstringbe.models.Transaction;
import com.toren.shewstringbe.service.BankAccountService;
import com.toren.shewstringbe.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

@Component
public class TransactionMapper {

    private final BankAccountService bankAccountService;
    private final UserProfileService userProfileService;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionMapper(BankAccountService bankAccountService, UserProfileService userProfileService,
                            ModelMapper modelMapper) {
        this.bankAccountService = bankAccountService;
        this.userProfileService = userProfileService;
        this.modelMapper = modelMapper;
    }

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

    public Transaction fromSubmitTransactionToTransaction(SubmitTransactionDto submitTransactionDto) {
        Transaction transaction = modelMapper.map(submitTransactionDto, Transaction.class);

        transaction.setBankAccount(bankAccountService
            .getBankAccountById(submitTransactionDto.getBankAccountId()).orElseThrow());
        transaction.setUserProfile(userProfileService
            .getUserProfileById(UUID.fromString(submitTransactionDto.getUserId())).orElseThrow());

        return transaction;
    }

    public ReturnTransactionDto fromTransactionToReturnTransactionDto(Transaction transaction) {
        ReturnTransactionDto returnTransactionDto = modelMapper.map(transaction, ReturnTransactionDto.class);

        returnTransactionDto.setBankAccountId(transaction.getBankAccount().getId());

        return returnTransactionDto;
    }

    public Transaction fromUpdateTransactionDtoToTransaction(UpdateTransactionDto updateTransactionDto) {
        Transaction transaction = modelMapper.map(updateTransactionDto, Transaction.class);

        transaction.setBankAccount(bankAccountService
            .getBankAccountById(updateTransactionDto.getBankAccountId()).orElseThrow());
        transaction.setUserProfile(userProfileService
            .getUserProfileById(UUID.fromString(updateTransactionDto.getUserId())).orElseThrow());

        return transaction;
    }
}
