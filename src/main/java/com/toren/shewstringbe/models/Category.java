package com.toren.shewstringbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toren.shewstringbe.enums.BudgetCategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
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

    @JsonBackReference("budget_categories")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetId")
    private Budget budget;
    
    @JsonManagedReference("category_transactions")
    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions = new ArrayList<>();

    
    @NotNull
    @JsonBackReference("user_categories")
    @ManyToOne(fetch = FetchType.LAZY)
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

    @Override
    public String toString() {
        return "Category{" +
            "amount=" + amount +
            ", type=" + type +
            ", title='" + title + '\'' +
            ", id=" + id +
            '}';
    }
}
