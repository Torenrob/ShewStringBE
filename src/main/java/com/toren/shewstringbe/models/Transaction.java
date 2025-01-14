package com.toren.shewstringbe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // @JsonBackReference("category_transactions")
    @JsonIgnore
    @NotBlank(message = "Transaction category is required")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private String description;
    private ZonedDateTime createdOn = ZonedDateTime.now(ZoneOffset.UTC);

    // @JsonBackReference("user_transactions")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userProfileId")
    private UserProfile userProfile;

    // @JsonBackReference("account_transactions")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankAccountId")
    private BankAccount bankAccount;

    // @Override
    // public String toString() {
    //     return "Transaction{" +
    //         "createdOn=" + createdOn +
    //         ", description='" + description + '\'' +
    //         ", category='" + category + '\'' +
    //         ", date=" + date +
    //         ", amount=" + amount +
    //         ", transactionType=" + transactionType +
    //         ", title='" + title + '\'' +
    //         ", id=" + id +
    //     '}';
    // }
}
