package com.samso.linkjoa.presentation.member.request;

import lombok.Value;

@Value
public class LoginRequest{
    String mail;
    String password;
}
