package com.toren.shewstringbe.dto.userdto;

import lombok.Data;

@Data
public class UpdateUserDto {
  private String userId;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
