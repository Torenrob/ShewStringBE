package com.toren.shewstringbe.models;

import com.toren.shewstringbe.models.converter.StringListConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class UserProfile {
    @Id
    private UUID id = UUID.randomUUID();

    @NotBlank(message = "User first name is required")
    private String firstName;

    @NotBlank(message = "User last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "User password is required")
    private String passwordHash;

    private ZonedDateTime createdOn = ZonedDateTime.now(ZoneOffset.UTC);

    @Convert(converter = StringListConverter.class)
    private List<String> categories = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts = new ArrayList<>();
}
