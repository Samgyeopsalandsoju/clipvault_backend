package com.samso.linkjoa.presentation.member.request;

import lombok.Value;

@Value
public class SignUpRequest{
    String mail;
    String password;
    String verifiedMail;
}
