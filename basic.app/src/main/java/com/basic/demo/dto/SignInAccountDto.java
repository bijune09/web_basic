package com.basic.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInAccountDto {
    private String accountUsername;

    private String accountPassword;

    private String accountEmail;

    private String userName;

    private String userBirthday;

}
