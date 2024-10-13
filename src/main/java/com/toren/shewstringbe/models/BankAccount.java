package com.toren.shewstringbe.models;

import com.toren.shewstringbe.base.BankAccountBase;
import com.toren.shewstringbe.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class BankAccount extends BankAccountBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bank Account title is required")
    private String title;

    @NotBlank(message = "Bank Account type is required")
    private AccountType accountType;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private UserProfile userProfile;
}
