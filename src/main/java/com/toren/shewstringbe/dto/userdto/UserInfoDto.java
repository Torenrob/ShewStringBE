package com.toren.shewstringbe.dto.userdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toren.shewstringbe.base.UserProfileBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoDto extends UserProfileBase {
  private String id;

  @Override
  @JsonIgnore
  public Object getTransactions() {
    return null;
  }

  @Override
  @JsonIgnore
  public Object getBankAccounts() {
    return null;
  }
}
