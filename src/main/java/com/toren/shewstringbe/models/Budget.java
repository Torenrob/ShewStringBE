package com.toren.shewstringbe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Budget {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "All time budget flag needed")
  private Boolean isAllTime = true;

  private LocalDate monthYear = null;

  @JsonBackReference("account_budgets")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bankAccountId")
  private BankAccount bankAccount;

  @JsonManagedReference("budget_categories")
  @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Category> budgetCategories = new ArrayList<>();

  @JsonBackReference("user_budgets")
  @NotNull(message = "Budget must have a UserProfile attached")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userProfileId")
  private UserProfile userProfile;

  @AssertTrue(message = "Must be labeled as All Time Budget or Monty/Year of budget given")
  public boolean isValid() {
    return (isAllTime && monthYear == null) || (!isAllTime && monthYear != null);
  }

  @Override
  public String toString() {
      return "Budget{" +
          "id=" + id +
          ", isAllTime='" + isAllTime +
          ", monthYear=" + monthYear +
          ", budgetCategories=" + budgetCategories +
      '}';
  }
}
