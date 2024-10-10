package com.toren.shewstringbe.models;

import com.toren.shewstringbe.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank(message = "Bank Account title is required")
    private String Title;

    @NotBlank(message = "Bank Account type is required")
    private AccountType AccountType;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<Transaction>transactions = new ArrayList<>();

    @ManyToOne
    private UserProfile userProfile;
}
