package com.toren.shewstringbe.models;

import com.toren.shewstringbe.base.TransactionBase;
import com.toren.shewstringbe.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Transaction extends TransactionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Transaction title is required")
    private String title;

    @NotBlank(message = "Transaction type is required")
    private TransactionType transactionType;

    @NotNull(message = "Transaction amount is required")
    @Column(precision=18,scale=2)
    private BigDecimal amount;

    @NotNull(message = "Transaction date is required")
    private LocalDate date;

    @NotBlank(message = "Transaction category is required")
    private String category;

    private String description;
    private ZonedDateTime createdOn = ZonedDateTime.now(ZoneOffset.UTC);

    @ManyToOne(fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.EAGER)
    private BankAccount bankAccount;
}
