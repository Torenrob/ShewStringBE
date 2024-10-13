package com.toren.shewstringbe.controller;

import com.toren.shewstringbe.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BankAccountController {


    @SuppressWarnings({"unused", "FieldCanBeLocal"})
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
}
