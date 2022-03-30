package com.example.rolepermission_security.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {
   private String fullName;
   private String username;
   private String password;
   private String prepassword;
}
