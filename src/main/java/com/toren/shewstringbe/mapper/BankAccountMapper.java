package com.toren.shewstringbe.mapper;

import com.toren.shewstringbe.dto.bankaccountdto.ReturnBankAccountDto;
import com.toren.shewstringbe.dto.bankaccountdto.SubmitBankAccountDto;
import com.toren.shewstringbe.models.BankAccount;
import com.toren.shewstringbe.models.UserProfile;
import com.toren.shewstringbe.service.UserProfileService;

import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BankAccountMapper {
    // private static final Logger log = LoggerFactory.getLogger(BankAccountMapper.class);
    private final UserProfileService userProfileService;
    private final TransactionMapper transactionMapper;

    @Autowired
    public BankAccountMapper(ModelMapper modelMapper, UserProfileService userProfileService, TransactionMapper transactionMapper) {
        this.userProfileService = userProfileService;
        this.transactionMapper = transactionMapper;
    }

    public ReturnBankAccountDto fromBankAccountToReturnBankAccountDto(BankAccount bankAccount) {
        ReturnBankAccountDto returnBankAccountDto = new ReturnBankAccountDto(transactionMapper);

        returnBankAccountDto.setId(bankAccount.getId());
        returnBankAccountDto.setTransactions(bankAccount.getTransactions());
        returnBankAccountDto.setTitle(bankAccount.getTitle());
        returnBankAccountDto.setBudgets(bankAccount.getBudgets());
        returnBankAccountDto.setAccountType(bankAccount.getAccountType());

        return returnBankAccountDto;
    }

    public BankAccount fromSubmitBankAccountDtoToBankAccount(SubmitBankAccountDto submit) {
        UserProfile userProfile = userProfileService.getUserProfileById(submit.getUserId()).orElseThrow();

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountType(submit.getAccountType());
        bankAccount.setTitle(submit.getTitle());
        bankAccount.setUserProfile(userProfile);

        return bankAccount;
    }
}
