package com.toren.shewstringbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toren.shewstringbe.enums.CategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String title;
    
    @NotNull
    private CategoryType type;
    
    @NotNull
    private BigDecimal budgetLimit;
    
    @NotNull
    @JsonBackReference("user_categories")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userProfileId")
    private UserProfile userProfile;
    
    public Category(){}
    
    public Category(String title, BigDecimal budgetLimit) {
        this.title = title;
        this.budgetLimit = budgetLimit;
    }
}
