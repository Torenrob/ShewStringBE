package com.toren.shewstringbe.models;

import com.toren.shewstringbe.base.UserProfileBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class UserProfile extends UserProfileBase implements UserDetails {
    @Id
    @UuidGenerator
    private String id;

    @Column(unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "User first name is required")
    private String firstName;

    @NotBlank(message = "User last name is required")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "User email is required")
    private String email;

    @NotBlank(message = "User password is required")
    private String password;

    private boolean isAccountNonExpired = true;
    
    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    @NotNull
    private ZonedDateTime createdOn = ZonedDateTime.now(ZoneOffset.UTC);

    // @JsonManagedReference("user_accounts")
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BankAccount> bankAccounts = new ArrayList<>();

    // @JsonManagedReference("user_budgets")
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Budget> budgets = new ArrayList<>();

    // @JsonManagedReference("user_categories")
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("createdAt ASC")
    private List<Category> categories = new ArrayList<>(List.of(new Category("None", new BigDecimal("0.00"))));

    // @JsonManagedReference("user_transactions")
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}


