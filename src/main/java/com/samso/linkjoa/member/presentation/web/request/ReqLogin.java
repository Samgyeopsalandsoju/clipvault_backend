package com.samso.linkjoa.member.presentation.web.request;

import lombok.Value;

@Value
public class ReqLogin {
    String mail;
    String password;
}
