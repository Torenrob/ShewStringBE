package com.toren.shewstringbe.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


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

  // @JsonBackReference("account_budgets")
  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "bankAccountId")
  private BankAccount bankAccount;

  // @JsonManagedReference("budget_categories")
  @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Category> budgetCategories = new ArrayList<>();

  // @JsonBackReference("user_budgets")
  @JsonIgnore
  @NotNull(message = "Budget must have a UserProfile attached")
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userProfileId")
  private UserProfile userProfile;

  @JsonIgnore
  @AssertTrue(message = "Must be labeled as All Time Budget or Monty/Year of budget given")
  public boolean isValid() {
    return (isAllTime && monthYear == null) || (!isAllTime && monthYear != null);
  }

  // @Override
  // public String toString() {
  //     return "Budget{" +
  //         "id=" + id +
  //         ", isAllTime='" + isAllTime +
  //         ", monthYear=" + monthYear +
  //         ", budgetCategories=" + budgetCategories +
  //     '}';
  // }
}
