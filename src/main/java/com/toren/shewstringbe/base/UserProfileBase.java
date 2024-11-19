package com.toren.shewstringbe.base;

import com.toren.shewstringbe.models.Category;
import lombok.Data;

import java.util.List;

@Data
public abstract class UserProfileBase {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Object transactions;
    private List<Category> categories;
    private Object bankAccounts;
}
