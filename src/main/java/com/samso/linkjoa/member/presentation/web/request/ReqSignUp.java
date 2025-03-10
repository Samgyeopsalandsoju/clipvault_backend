package com.samso.linkjoa.member.presentation.web.request;

import lombok.Value;

@Value
public class ReqSignUp {
    String mail;
    String password;
    String verifiedMail;
}
