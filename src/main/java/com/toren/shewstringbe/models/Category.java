package com.toren.shewstringbe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toren.shewstringbe.enums.BudgetCategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String title;
    
    @NotNull
    private BudgetCategoryType type;
    
    @NotNull
    private BigDecimal amount;

    private String color;
    
    @NotNull
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC);

    // @JsonBackReference("budget_categories")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "budgetId")
    private Budget budget;
    
    // @JsonManagedReference("category_transactions")
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    
    @NotNull
    // @JsonBackReference("user_categories")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userProfileId")
    private UserProfile userProfile;

    public Category(){}

    public Category(String title, BigDecimal amount) {
        this.title = title;
        this.amount = amount;
    }

    public Category(String title, BigDecimal amount, BudgetCategoryType type) {
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.color = (type == BudgetCategoryType.Income) ? "#009A1E" : "#DE362A";
    }

    // @Override
    // public String toString() {
    //     return "Category{" +
    //         "amount=" + amount +
    //         ", type=" + type +
    //         ", title='" + title + '\'' +
    //         ", id=" + id +
    //         '}';
    // }
}
